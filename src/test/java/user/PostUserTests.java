package user;

import commons.TestBase;
import io.restassured.response.Response;
import models.Constants;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static requests.UserEndpoint.*;

public class PostUserTests extends TestBase {
    private User validUser;

    @BeforeClass
    public void generateTestData() {
        validUser = new User("User Clarice", "new@email.com", "321321", "true");
    }

    @AfterClass
    public void removeTestData() {
        deleteUserRequest(SPEC, validUser);
    }

    @Test
    public void shouldReturnSuccessMessageNewUserAndStatus200() {
        Response createUserSuccessResponse = registerUserRequest(SPEC, validUser);
        createUserSuccessResponse.
                then().log().body().
                assertThat().
                statusCode(201).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_REGISTER));
    }
}
