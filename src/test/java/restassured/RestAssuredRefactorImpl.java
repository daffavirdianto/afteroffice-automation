package restassured;

import org.testng.annotations.Test;

import apiengine.Endpoints;

import io.restassured.response.Response;
import utils.RequestPayloads;

public class RestAssuredRefactorImpl extends Endpoints {

    String token;
    Integer id;

    @Test
    public void testRegister() {
        
        String uniqueEmail = "daffa.virdianto" + (System.currentTimeMillis() % 1000) + "@gmail.com";
        String bodyRequest = RequestPayloads.getRegisterPayload(uniqueEmail);
        Response response = registerUser(bodyRequest);
        
        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Registration failed";
        assert response.jsonPath().getString("email").equals(uniqueEmail) : "Email mismatch";
        assert response.jsonPath().getString("full_name").equals("Daffa Virdianto") : "Full name mismatch";
        assert response.jsonPath().getString("department").equals("Human Resource") : "Department mismatch";
        assert response.jsonPath().getString("phone_number").equals("081325018827") : "Phone number mismatch";
    }

    @Test(priority = 1)
    public void testLogin(){

        String bodyRequest = RequestPayloads.getLoginPayload();
        Response response = loginUser(bodyRequest);
        
        token = response.jsonPath().getString("token");

        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Login failed";
        assert token != null : "Token is null";
    }

    @Test(dependsOnMethods = "testLogin")
    public void testGetListAllObjects() {

        Response response = getListAllObjects(token);
        
        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Failed to get list of objects";
    }

    @Test(dependsOnMethods = "testLogin", priority = 2)
    public void testAddObject() {
        
        String bodyRequest = RequestPayloads.getAddObjectPayload();
        Response response = addObject(token, bodyRequest);
        
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
        
        Response response = getSingleObject(token, id);
        
        System.out.println("Response: " + response.asPrettyString());
    }
    
    @Test(dependsOnMethods = "testLogin", priority = 4)
    public void ListOfObjectsByIds() {

        Response response = getListOfObjectsByIds(token, id);
        System.out.println("Response: " + response.asPrettyString());
    }

@Test(dependsOnMethods = "testLogin", priority = 5)
    public void testUpdateObject() {

        String bodyRequest = RequestPayloads.updateObjectPayload();
        Response response = updateObject(token, id, bodyRequest);
        
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

        String requestBody = RequestPayloads.partiallyUpdateObject();
        Response response = partiallyUpdateObject(token, id, requestBody);
        
        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Failed to partially update object";
        assert response.jsonPath().getString("name").equals("Apple MacBook Pro 1611-daffa") : "Object name mismatch";
        assert response.jsonPath().getString("data.year").equals("2030") : "Object year mismatch";
    }

    @Test(dependsOnMethods = "testLogin", priority = 7)
    public void testDeleteObject() {

        Response response = deleteObject(token, id);
        
        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Failed to delete object";
        assert response.jsonPath().getString("status").equals("deleted") : "Delete message mismatch";
    }

    @Test(dependsOnMethods = "testLogin")
    public void testGetAllDepartments() {

        Response response = getAllDepartments(token);
        
        System.out.println("Response: " + response.asPrettyString());
        assert response.statusCode() == 200 : "Failed to get list of departments";
        
        String[] expectedDepartments = {"Technology", "Human Resource", "Finance", "Executive"};
        for (int i = 0; i < expectedDepartments.length; i++) {
            assert response.jsonPath().getString("[" + i + "].department").equals(expectedDepartments[i]) 
            : "Department name mismatch at index " + i;
        }
    }
}
