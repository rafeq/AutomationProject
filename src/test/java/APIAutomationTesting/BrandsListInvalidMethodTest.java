package APIAutomationTesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BrandsListInvalidMethodTest {

    @Test
    public void testInvalidMethodOnBrandsList() {
        // Set base URI for RestAssured
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Send PUT request to /brandsList and capture the response
        Response response = given()
                .contentType("application/json") // Optional: Specify the content type
                .when()
                .put("/brandsList")             // Use an unsupported PUT method
                .then()
                .log().all()                    // Log the full response for debugging
                .extract().response();

        // Log the response details
        System.out.println("Request URL: " + RestAssured.baseURI + "/brandsList");
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Status Code: " + response.getStatusCode());

        // Validate the status code
        response.then().statusCode(equalTo(200));

    }
}
