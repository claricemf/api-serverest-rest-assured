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

public class PutUserTests extends TestBase {
    private User validUser;

    @BeforeClass
    public void generateTestData() {
        validUser = new User("User Tester", "user_tester@email.com", "321321", "true");
        registerUserRequest(SPEC, validUser);
        validUser.setEmail("new_user_tester@email.com");
        validUser.setName("New User Tester");
    }

    @AfterClass
    public void removeTestData() {
        deleteUserRequest(SPEC, validUser);
    }

    @Test
    public void shouldReturnSuccessModifyUserAndStatus200(){
        Response modifyUserSuccessResponse = putUserRequest(SPEC, validUser);
        modifyUserSuccessResponse.
                then().log().body().
                assertThat().
                statusCode(200).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_PUT));
    }

}
