package product;

import io.restassured.response.Response;
import commons.TestBase;
import models.Product;
import models.User;
import org.testng.annotations.*;

import static org.hamcrest.Matchers.equalTo;
import static requests.ProductEndpoint.registerProductRequest;
import static requests.ProductEndpoint.deleteProductRequest;
import static requests.ProductEndpoint.getProductRequest;
import static requests.ProductEndpoint.getAllProductRequest;
import static requests.UserEndpoint.*;


public class GetProductTests extends TestBase {
    private User validUser;
    private Product validProduct;

    @BeforeClass
    public void generateTestData() {
        validUser = new User("User Clarice", "clarice@email.com", "321123", "true");
        validProduct = new Product("Notebook Tester", 1000, "Desktop", 10);
        registerUserRequest(SPEC, validUser);
        authenticateUserRequest(SPEC, validUser);
        registerProductRequest(SPEC, validProduct, validUser);
    }

    @AfterClass
    public void removeTestData() {
        deleteProductRequest(SPEC, validProduct, validUser);
        deleteUserRequest(SPEC, validUser);
    }

    @Test
    public void shouldReturnProductAndStatus200(){
        Response getProductResponse = getProductRequest(SPEC, validProduct, validUser);
        getProductResponse.
                then().log().body().
                assertThat().
                statusCode(200).
                body("_id", equalTo(validProduct.getProductID()));

    }

    @Test
    public void shouldReturnAllProductAndStatus200(){
        Response getProductAllResponse = getAllProductRequest(SPEC, "?nome="+validProduct.getName(), validUser);
        getProductAllResponse.
                then().log().body().
                assertThat().
                statusCode(200).
                body("quantidade", equalTo(1));

    }
}
