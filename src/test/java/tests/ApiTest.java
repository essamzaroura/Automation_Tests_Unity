package tests;

import apiClients.ApiClient;
import apiClients.PostClient;
import apiClients.PublisherClient;
import entities.Post;
import entities.Publisher;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {
        private Publisher publisher;
        private Post post;
        private RequestSpecification authenticatedRequest;

        @BeforeMethod
        public void setUp() {
                ApiClient client = new ApiClient();
                RequestSpecification baseRequest = client.request;

                Response loginResponse = baseRequest
                                .contentType("application/x-www-form-urlencoded")
                                .formParams("email", "admin@example.com",
                                                "password", "password")
                                .post("/admin/login");

                System.out.println("--- Login Response START ---");
                loginResponse.then().log().all();
                System.out.println("--- Login Response END ---");

                int statusCode = loginResponse.getStatusCode();
                if (!(statusCode == 302 || statusCode == 200)) {
                        throw new RuntimeException(
                                        "Login failed with status: " + statusCode + ". Response: "
                                                        + loginResponse.asString());
                }

                Map<String, String> cookies = loginResponse.getCookies();
                System.out.println("Received Cookies after login: " + cookies);

                this.authenticatedRequest = given()
                                .baseUri(ApiClient.BASE_URL)
                                .cookies(cookies)
                                .log().all();
        }

        @Test
        public void testPostStatusChange() {
                // Step 1: Create Publisher
                String uniqueEmail = "TestPublisher_API_" + UUID.randomUUID().toString().substring(0, 8)
                                + "@hotmail.com";
                String publisherName = "TestPublisher_API_" + UUID.randomUUID().toString().substring(0, 8);
                publisher = new Publisher(publisherName, uniqueEmail);
                Response pubResponse = new PublisherClient(authenticatedRequest).createPublisher(publisher);
                Assert.assertEquals(pubResponse.getStatusCode(), 200, "Publisher creation failed");
                String publisherId = pubResponse.jsonPath().getString("record.params.id");
                publisher.setId(publisherId);

                // Step 2+3: Create Post using the publisher
                post = new Post("TestPost_API1", "ACTIVE", publisher);
                Response postResponse = new PostClient(authenticatedRequest).createPost(post);
                Assert.assertEquals(postResponse.getStatusCode(), 200, "Post creation failed.");
                String actualStatus = postResponse.jsonPath().getString("record.params.status");
                Assert.assertEquals(actualStatus, "ACTIVE", "Post status is not ACTIVE after creation.");

                // Step 4+5: Change Post Status.
                String postId = postResponse.jsonPath().getString("record.params.id");
                Assert.assertNotNull(postId, "Post ID was null, could not proceed with status change.");
                Response updateResponse = new PostClient(authenticatedRequest).changePostStatus(postId, "REMOVED");
                Assert.assertEquals(updateResponse.getStatusCode(), 200, "Post status update failed. Expected 200.");

                // Step 6: Verify Post status update was successful.
                String updatedPostStatus = updateResponse.jsonPath().getString("record.params.status");
                Assert.assertEquals(updatedPostStatus, "REMOVED",
                                "Post status did not change to " + "REMOVED" + " after update.");
        }
}