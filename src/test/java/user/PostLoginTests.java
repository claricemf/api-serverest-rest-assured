package user;

import io.restassured.response.Response;
import models.User;
import models.Constants;
import commons.TestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static requests.UserEndpoint.*;
import static requests.UserEndpoint.authenticateUserRequest;

public class PostLoginTests extends TestBase {

    private User validUser;
    private User invalidUser;

    @BeforeClass
    public void generateTestData(){
        validUser = new User("User Login", "login_valid_user@email.com", "abd123", "true");
        registerUserRequest(SPEC, validUser);
        invalidUser = new User("User Login Invalid", "login_invalid_user@email.com", "123abc", "true");
    }

    @AfterClass
    public void removeTestData(){
        deleteUserRequest(SPEC, validUser);
    }

    @Test
    public void shouldReturnSuccessMessageAuthTokenAndStatus200(){
        Response loginSuccessResponse = authenticateUserRequest(SPEC, validUser);
        loginSuccessResponse.
                then().log().body().
                assertThat().
                statusCode(200).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_LOGIN)).
                body("authorization", notNullValue());

    }

    @Test
    public void shouldReturnFailureMessageAndStatus401(){

        Response loginFailureResponse = authenticateUserRequest(SPEC, invalidUser);
        loginFailureResponse.
                then().log().body().
                assertThat().
                statusCode(401).
                body("message", equalTo(Constants.MESSAGE_FAILED_LOGIN)).
                body("authorization", nullValue());
    }
}
