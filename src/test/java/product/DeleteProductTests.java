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
import static requests.ProductEndpoint.deleteProductRequest;
import static requests.UserEndpoint.*;

public class DeleteProductTests extends TestBase {
    private User validUser;
    private Product validProduct;

    @BeforeClass
    public void generateTestData() {
        validUser = new User("User Delete", "delete@email.com", "123456", "true");
        validProduct = new Product("Computador Default", 1000, "Desktop", 10);
        registerUserRequest(SPEC, validUser);
        authenticateUserRequest(SPEC, validUser);
        registerProductRequest(SPEC, validProduct, validUser);
    }

    @AfterClass
    public void removeTestData() {
        deleteUserRequest(SPEC, validUser);
    }

    @Test
    public void shouldRemoveProductReturnSuccessMessageAndStatus200() {
        Response deleteProductResponse = deleteProductRequest(SPEC, validProduct, validUser);
        deleteProductResponse.
                then().
                assertThat().
                statusCode(200).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_DELETE));
    }

}
