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
import static requests.ProductEndpoint.putProductRequest;
import static requests.ProductEndpoint.deleteProductRequest;
import static requests.UserEndpoint.*;

public class PutProductTests extends TestBase {
    private User validUser;
    private Product validProduct;
    private Product changedProduct;

    @BeforeClass
    public void generateTestData(){
        validUser = new User("User Clarice", "clarice@email.com", "321321", "true");
        validProduct = new Product("Mouse", 2000, "Slim", 250);
        registerUserRequest(SPEC, validUser);
        authenticateUserRequest(SPEC,validUser);
        registerProductRequest(SPEC,validProduct, validUser);
        changedProduct = new Product("Mouse sem Fio", 2500, "Slim Red", 250);
    }

    @AfterClass
    public void removeTestData(){
        deleteUserRequest(SPEC, validUser);
        deleteProductRequest(SPEC,validProduct, validUser);
    }

    @Test
    public void shouldReturnSuccessModifyPriceProductAndStatus200(){
        Response modifyProductSuccessResponse = putProductRequest(SPEC, changedProduct, validUser);
        modifyProductSuccessResponse.
                then().log().body().
                assertThat().
                statusCode(201).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_REGISTER));
    }
}
