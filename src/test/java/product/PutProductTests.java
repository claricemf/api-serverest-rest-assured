package product;

import commons.TestBase;
import io.restassured.response.Response;
import models.Constants;
import models.Product;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static requests.ProductEndpoint.registerProductRequest;
import static requests.ProductEndpoint.putProductRequest;
import static requests.ProductEndpoint.deleteProductRequest;
import static requests.UserEndpoint.*;

public class PutProductTests extends TestBase {
    private User validUser;
    private Product validProduct;

    @BeforeClass
    public void generateTestData(){
        validUser = new User("User Test", "test_email_product@email.com", "321321", "true");
        validProduct = new Product("Product Modify", 2000, "Description Modify", 250);
        registerUserRequest(SPEC, validUser);
        authenticateUserRequest(SPEC,validUser);
        registerProductRequest(SPEC,validProduct, validUser);
        validProduct.setPrice(2500);
        validProduct.setName("Product Modified");
        validProduct.setAmount(100);
        validProduct.setDescription("Description Modified");
    }

    @AfterClass
    public void removeTestData(){
        deleteProductRequest(SPEC,validProduct, validUser);
        deleteUserRequest(SPEC, validUser);
    }

    @Test
    public void shouldReturnSuccessModifyProductAndStatus200(){
        Response modifyProductSuccessResponse = putProductRequest(SPEC, validProduct, validUser);
        modifyProductSuccessResponse.
                then().log().body().
                assertThat().
                statusCode(200).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_PUT));
    }
}
