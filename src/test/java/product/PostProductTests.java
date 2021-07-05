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
import static requests.ProductEndpoint.deleteProductRequest;
import static requests.ProductEndpoint.registerProductRequest;
import static requests.UserEndpoint.authenticateUserRequest;
import static requests.UserEndpoint.registerUserRequest;
import static requests.UserEndpoint.deleteUserRequest;

public class PostProductTests extends TestBase {
    private User validUser;
    private Product validProduct;

    @BeforeClass
    public void generateTestData() {
        validUser = new User("User Clarice", "clarice@email.com", "321321", "true");
        validProduct = new Product("Notebook New", 1000, "Desktop", 10);
        registerUserRequest(SPEC, validUser);
        authenticateUserRequest(SPEC, validUser);
    }

    @AfterClass
    public void removeTestData() {
        deleteProductRequest(SPEC, validProduct, validUser);
        deleteUserRequest(SPEC, validUser);
    }

    @Test
    public void shouldReturnSuccessMessageNewProductAndStatus201() {
        Response createProductSuccessResponse = registerProductRequest(SPEC, validProduct, validUser);
        createProductSuccessResponse.
                then().log().body().
                assertThat().
                statusCode(201).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_REGISTER));
    }

}
