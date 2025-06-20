package schemavalidation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidation {

    @Test
    public void testSchemaValidationRegister() {
        String uniqueEmail = "daffa.virdianto" + (System.currentTimeMillis() % 1000) + "@gmail.com";

        RestAssured.baseURI = "https://whitesmokehouse.com";

        String requestBody = "{\n" +
            "  \"email\": \"" + uniqueEmail + "\",\n" +
            "  \"full_name\": \"Daffa Virdianto\",\n" +
            "  \"password\": \"D@ffa123\",\n" +
            "  \"department\": \"Human Resource\",\n" +
            "  \"phone_number\": \"081325018827\"\n" +
            "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .when()
                .post("/webhook/api/register");

        System.out.println("Response: " + response.asPrettyString());

        try {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("register-schema.json"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    
}
