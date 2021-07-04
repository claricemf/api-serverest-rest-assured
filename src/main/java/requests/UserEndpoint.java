package requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import static io.restassured.RestAssured.given;

public class UserEndpoint extends RequestBase {
    public static Response authenticateUserRequest(RequestSpecification spec, User user) {
        Response loginResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        and().
                        body(user.getUserCredentialsAsJson()).
                        when().post("login");

        user.setUserAuthToken(getValueFromResponse(loginResponse, "authorization"));
        return loginResponse;
    }

    public static Response getUserRequest(RequestSpecification spec, String query) {
        Response getUserResponse =
                given().
                        spec(spec).
                        when().
                        get("usuarios" + query);
        return getUserResponse;
    }

    public static Response registerUserRequest(RequestSpecification spec, User user) {
        Response registerUserResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        and().
                        body(user.getUserJsonRepresentationAsString()).
                        when().
                        post("usuarios");

        user.setUserId(getValueFromResponse(registerUserResponse, "_id"));
        return registerUserResponse;
    }

    public static Response putUserRequest(RequestSpecification spec, User user) {
        Response putUserResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        and().
                        body(user.getUserJsonRepresentationAsString()).
                        when().
                        put("usuarios/" + user.userID);
        return putUserResponse;
    }

    public static Response deleteUserRequest(RequestSpecification spec, User user) {
        Response deleteUserResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        when().
                        delete("usuarios/" + user.userID);
        return deleteUserResponse;
    }
}