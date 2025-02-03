package APIAutomationTesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.AnyOf.anyOf;

public class GetUserAccountDetailsTest {

    @Test
    public void testGetUserDetailByEmail() {
        // Set base URI for RestAssured
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Email for which we want to fetch the user details
        String email = "johndoe@example.com";

        // Send GET request to get user details by email
        Response response = given()
                .queryParam("email", email)  // Add query parameter 'email'
                .when()
                .get("/getUserDetailByEmail")  // Endpoint
                .then()
                .log().all()  // Log full response for debugging
                .extract().response();

        // Log the response details
        System.out.println("Request URL: " + RestAssured.baseURI + "/getUserDetailByEmail?email=" + email);
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Content-Type: " + response.getHeader("Content-Type"));

        // Parse response body manually as JSON
        String responseBody = response.getBody().asString();

        // Check the Content-Type header
        if (!response.getHeader("Content-Type").contains("application/json")) {
            System.out.println("Warning: Unexpected Content-Type. Expected 'application/json', but got: " + response.getHeader("Content-Type"));
        }

        // Validate the status code
        response.then().statusCode(equalTo(200));
    }
}
