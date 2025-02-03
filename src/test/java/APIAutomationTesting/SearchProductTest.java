package APIAutomationTesting;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SearchProductTest {

    @Test
    public void testSearchProduct() {
        // Set base URI for RestAssured
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Search product keyword
        String searchKeyword = "tshirt";

        // Send POST request to search for the product
        Response response = given()
                .contentType("application/x-www-form-urlencoded") // Form data content type
                .formParam("search_product", searchKeyword)       // Request parameter
                .when()
                .post("/searchProduct")                          // Endpoint
                .then()
                .log().all()                                     // Log full response for debugging
                .extract().response();

        // Log the response details
        System.out.println("Request URL: " + RestAssured.baseURI + "/searchProduct");
        System.out.println("Request Parameter: search_product = " + searchKeyword);
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Status Code: " + response.getStatusCode());

        // Validate the status code
        response.then().statusCode(equalTo(200));

    }
}

