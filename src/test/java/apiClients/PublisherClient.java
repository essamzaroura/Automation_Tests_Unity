package apiClients;

import entities.Publisher;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class PublisherClient extends ApiClient {
    private RequestSpecification requestSpec;

    // Correct the endpoint
    private static final String PUBLISHER_ENDPOINT = "/admin/api/resources/Publisher/actions/new";

    public PublisherClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response createPublisher(Publisher publisher) {
        return requestSpec
                .contentType("multipart/form-data")
                .multiPart("name", publisher.getName())
                .multiPart("email", publisher.getEmail())

                .post(PUBLISHER_ENDPOINT);
    }
}