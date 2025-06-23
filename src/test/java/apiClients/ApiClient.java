package apiClients;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiClient {
    public static final String BASE_URL = "http://localhost:3000";

    public RequestSpecification request;

    public ApiClient() {
        RestAssured.baseURI = BASE_URL;
        this.request = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json");
    }
}