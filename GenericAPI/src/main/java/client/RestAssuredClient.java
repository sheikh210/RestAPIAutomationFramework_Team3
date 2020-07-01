package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RestAssuredClient {

    //JsonPlaceholder Endpoints
    private final String ENDPOINT_POSTS = "/posts";
    private final String ENDPOINT_COMMENTS = "/comments";
    private final String ENDPOINT_USERS = "/users";

    public String getENDPOINT_POSTS() {
        return ENDPOINT_POSTS;
    }

    public String getENDPOINT_COMMENTS() {
        return ENDPOINT_COMMENTS;
    }

    public String getENDPOINT_USERS() {
        return ENDPOINT_USERS;
    }

    public Response get(String url){
        RestAssured.defaultParser = Parser.JSON;

        return given().when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public ValidatableResponse post(HashMap jsonBody, String url){
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(jsonBody).when().post(url).then();
    }

    public ValidatableResponse patch(HashMap jsonBody, String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(jsonBody).when().patch(url).then();
    }

}
