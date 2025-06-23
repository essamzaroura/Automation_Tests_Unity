package apiClients;

import entities.Post;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class PostClient extends ApiClient {
    private RequestSpecification baseAuthenticatedRequest;

    private static final String POST_CREATE_ENDPOINT = "/admin/api/resources/Post/actions/new";
    private static final String POST_STATUS_CHANGE_ENDPOINT_PREFIX = "/admin/api/resources/Post/";
    private static final String POST_STATUS_CHANGE_ENDPOINT_SUFFIX = "/actions/changeStatus";

    public PostClient(RequestSpecification authenticatedRequest) {
        this.baseAuthenticatedRequest = authenticatedRequest;
    }

    public Response createPost(Post post) {
        return baseAuthenticatedRequest
                .given()
                .contentType("multipart/form-data")
                .multiPart("title", post.getTitle())
                .multiPart("status", post.getStatus())
                .multiPart("publisher", post.getPublisher().getId())
                .post(POST_CREATE_ENDPOINT);
    }

    public Response changePostStatus(String postId, String newStatus) {
        return baseAuthenticatedRequest
                .given()
                .contentType("application/json")
                .body(Map.of("status", newStatus))
                .put(POST_STATUS_CHANGE_ENDPOINT_PREFIX + postId + POST_STATUS_CHANGE_ENDPOINT_SUFFIX);
    }
}