package requests;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Product;
import models.User;
import static io.restassured.RestAssured.given;

public class ProductEndpoint extends RequestBase {

    public static Response registerProductRequest(RequestSpecification spec, Product product, User user) {
         Response registerProductResponse =
                given().
                        spec(spec).
                        header("Authorization", user.authToken, "Content-Type", "application/json").
                        and().
                        body(product.getProductJsonRepresentationAsString()).
                        when().
                        post("produtos");

        product.setProductId(getValueFromResponse(registerProductResponse, "_id"));
        return registerProductResponse;
    }

    public static Response deleteProductRequest(RequestSpecification spec, Product product, User user) {
        Response deleteProductResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        header("Authorization", user.authToken).
                        when().
                        delete("produtos/" + product.productID);
        return deleteProductResponse;
    }

    public static Response getProductRequest(RequestSpecification spec, Product product, User user) {
        Response getProductResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        header("Authorization", user.authToken).
                        when().
                        get("produtos/" + product.productID);
        return getProductResponse;
    }

    public static Response getAllProductRequest(RequestSpecification spec, String query, User user) {
        Response getAllProductResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        header("Authorization", user.authToken).
                        when().
                        get("produtos/" + query);
        return getAllProductResponse;
    }

    public static Response putProductRequest(RequestSpecification spec, Product product, User user) {
        Response putProductResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        header("Authorization", user.authToken).
                        and().
                        body(product.getProductJsonRepresentationAsString()).
                        when().
                        put("produtos/" + product.productID);
        return putProductResponse;
    }
}
