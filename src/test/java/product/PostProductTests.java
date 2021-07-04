package product;

import commons.TestBase;
import io.restassured.response.Response;
import models.Constants;
import models.Product;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static requests.ProductEndpoint.registerProductRequest;
import static requests.UserEndpoint.*;

public class PostProductTests extends TestBase {
    private User validUser;
    private Product validProduct;

    @BeforeClass
    public void generateTestData(){
        validUser = new User("Maria Clarice", "clarice123@email.com", "321321", "true");
        registerUserRequest(SPEC, validUser);
        authenticateUserRequest(SPEC,validUser);
        validProduct = new Product("Computador", 1000, "Desktop", 10);
    }

    @AfterClass
    public void removeTestData(){
        deleteUserRequest(SPEC, validUser);
    }

    @Test
    public void shouldReturnSuccessMessageNewProductAndStatus200(){
        Response createProductSuccess = registerProductRequest(SPEC, validProduct, validUser);
        createProductSuccess.
                then().
                assertThat().
                statusCode(200).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_NEW_PRODUCT));
    }

}
