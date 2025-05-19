package restassured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredImpl {

    String token;
    Integer id;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://whitesmokehouse.com";
    }

    @Test
    public void testRegister() {

        String requestBody = "{\n" +
            "  \"email\": \"daffa.virdianto5@gmail.com\",\n" +
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

        assert response.statusCode() == 200 : "Registration failed";
        assert response.jsonPath().getString("email").equals("daffa.virdianto5@gmail.com") : "Email mismatch";
        assert response.jsonPath().getString("full_name").equals("Daffa Virdianto") : "Full name mismatch";
        assert response.jsonPath().getString("department").equals("Human Resource") : "Department mismatch";
        assert response.jsonPath().getString("phone_number").equals("081325018827") : "Phone number mismatch";
    }

    @Test(priority = 1)
    public void testLogin(){

        String requestBody = "{\n" +
            "  \"email\": \"daffa.virdianto1@gmail.com\",\n" +
            "  \"password\": \"D@ffa123\"\n" +
            "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
                .when()
                .post("/webhook/api/login");
        
        token = response.jsonPath().getString("token");

        System.out.println("Response: " + response.asPrettyString());

        assert response.statusCode() == 200 : "Login failed";
        assert token != null : "Token is null";
    }

    @Test(dependsOnMethods = "testLogin")
    public void testGetListAllObjects() {

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/api/objects");
        
        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Failed to get list of objects";
    }

    @Test(dependsOnMethods = "testLogin", priority = 2)
    public void testAddObject() {
        
        String requestBody = "{\n" +
        "  \"name\": \"Apple MacBook Pro 16\",\n" +
        "  \"data\": {\n" +
        "    \"year\": 2019,\n" +
        "    \"price\": 1849.99,\n" +
        "    \"cpu_model\": \"Intel Core i9\",\n" +
        "    \"hard_disk_size\": \"1 TB\",\n" +
        "    \"capacity\": \"2 cpu\",\n" +
        "    \"screen_size\": \"14 Inch\",\n" +
        "    \"color\": \"red\"\n" +
        "  }\n" +
        "}";
        
        Response response = RestAssured.given()
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + token)
        .body(requestBody)
        .log().all()
        .when()
        .post("/webhook/api/objects");
        
        id = response.jsonPath().getInt("[0].id");
        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Failed to add object";
        assert response.jsonPath().getString("[0].name").equals("Apple MacBook Pro 16") : "Object name mismatch";
        assert response.jsonPath().getInt("[0].data.year") == 2019 : "Object year mismatch";
        assert response.jsonPath().getDouble("[0].data.price") == 1849.99 : "Object price mismatch";
        assert response.jsonPath().getString("[0].data.cpu_model").equals("Intel Core i9") : "Object CPU model mismatch";
        assert response.jsonPath().getString("[0].data.hard_disk_size").equals("1 TB") : "Object hard disk size mismatch";
        assert response.jsonPath().getString("[0].data.capacity").equals("2 cpu") : "Object capacity mismatch";
        assert response.jsonPath().getString("[0].data.screen_size").equals("14 Inch") : "Object screen size mismatch";
        assert response.jsonPath().getString("[0].data.color").equals("red") : "Object color mismatch";
    }

    @Test(dependsOnMethods = "testLogin", priority = 3)
    public void SingleObject() {
        
        Response response = RestAssured.given()
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + token)
        .log().all()
        .when()
        .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + id);
        
        System.out.println("Response: " + response.asPrettyString());
    }
    
    @Test(dependsOnMethods = "testLogin", priority = 4)
    public void ListOfObjectsByIds() {

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .params("id", "3")
                .log().all()
                .when()
                .get("/webhook/api/objectslistId?id=" + id);
        
        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(dependsOnMethods = "testLogin", priority = 5)
    public void testUpdateObject() {

        String requestBody = "{\n" +
            "  \"name\": \"Apple MacBook Pro 18\",\n" +
            "  \"data\": {\n" +
            "    \"year\": 2019,\n" +
            "    \"price\": 1849.99,\n" +
            "    \"cpu_model\": \"Intel Core i9\",\n" +
            "    \"hard_disk_size\": \"1 TB\",\n" +
            "    \"capacity\": \"2 cpu\",\n" +
            "    \"screen_size\": \"14 Inch\",\n" +
            "    \"color\": \"blue\"\n" +
            "  }\n" +
            "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .log().all()
                .when()
                .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + id);
        
        System.out.println("Response: " + response.asPrettyString());

        assert response.statusCode() == 200 : "Failed to update object";
        assert response.jsonPath().getString("[0].name").equals("Apple MacBook Pro 18") : "Object name mismatch";
        assert response.jsonPath().getInt("[0].data.year") == 2019 : "Object year mismatch";
        assert response.jsonPath().getDouble("[0].data.price") == 1849.99 : "Object price mismatch";
        assert response.jsonPath().getString("[0].data['CPU model']").equals("Intel Core i9") : "Object CPU model mismatch";
        assert response.jsonPath().getString("[0].data['Hard disk size']").equals("1 TB") : "Object hard disk size mismatch";
        assert response.jsonPath().getString("[0].data.capacity").equals("2 cpu") : "Object capacity mismatch";
        assert response.jsonPath().getString("[0].data.screen_size").equals("14 Inch") : "Object screen size mismatch";
        assert response.jsonPath().getString("[0].data.color").equals("blue") : "Object color mismatch";
    }

    @Test(dependsOnMethods = "testLogin", priority = 6)
    public void PartiallyUpdateObject() {

        String requestBody = "{\n" +
            "  \"name\": \"Apple MacBook Pro 1611-daffa\",\n" +
            "  \"year\": \"2030\"\n" +
            "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .log().all()
                .when()
                .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/" + id);
        
        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Failed to partially update object";
        assert response.jsonPath().getString("name").equals("Apple MacBook Pro 1611-daffa") : "Object name mismatch";
        assert response.jsonPath().getString("data.year").equals("2030") : "Object year mismatch";
    }

    @Test(dependsOnMethods = "testLogin", priority = 7)
    public void testDeleteObject() {

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + id);
        
        System.out.println("Response: " + response.asPrettyString());

        assert response.statusCode() == 200 : "Failed to delete object";
        assert response.jsonPath().getString("status").equals("deleted") : "Delete message mismatch";
    }

    @Test(dependsOnMethods = "testLogin")
    public void testGetAllDepartments() {

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/api/department");
        
        System.out.println("Response: " + response.asPrettyString());

        assert response.statusCode() == 200 : "Failed to get list of departments";
        assert response.jsonPath().getString("[0].department").equals("Technology") : "Department name mismatch";
        assert response.jsonPath().getString("[1].department").equals("Human Resource") : "Second department name mismatch";
        assert response.jsonPath().getString("[2].department").equals("Finance") : "Third department name mismatch";
        assert response.jsonPath().getString("[3].department").equals("Executive") : "Fourth department name mismatch";
    }
}