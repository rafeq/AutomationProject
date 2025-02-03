package APIAutomationTesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.AnyOf.anyOf;

public class RegisterUserAPITest {

    @Test
    public void testCreateAccount() {
        // Set base URI for RestAssured (correct URI format)
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Set the request body (parameters for account creation)
        String requestBody = "{\n" +
                "\"name\": \"2John 2Doe\",\n" +
                "\"email\": \"9284johndoe@example.com\",\n" +
                "\"password\": \"password123\",\n" +
                "\"title\": \"Mr\",\n" +
                "\"birth_date\": \"15\",\n" +
                "\"birth_month\": \"April\",\n" +
                "\"birth_year\": \"1990\",\n" +
                "\"firstname\": \"John\",\n" +
                "\"lastname\": \"Doe\",\n" +
                "\"company\": \"Doe Industries\",\n" +
                "\"address1\": \"1234 Elm Street\",\n" +
                "\"address2\": \"Apt 101\",\n" +
                "\"country\": \"USA\",\n" +
                "\"zipcode\": \"12345\",\n" +
                "\"state\": \"California\",\n" +
                "\"city\": \"Los Angeles\",\n" +
                "\"mobile_number\": \"1234567890\"\n" +
                "}";

        // Send POST request to create account and capture the response
        Response response = given()
                .contentType("application/json")
                .body(requestBody)  // Pass the request body
                .when()
                .post("/createAccount")  // Endpoint here
                .then()
                .log().all()  // Log the full response for debugging
                .extract().response();

        // Log request details
        System.out.println("Request Body: " + requestBody);
        System.out.println("Response Body: " + response.getBody().asString());

        // Check the status code - can be either 200 or 201
        response.then().statusCode(200);
    }
}
