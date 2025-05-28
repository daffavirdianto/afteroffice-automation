package apiengine;

import cucumber.definitions.ObjectDefinition;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Endpoints {

    public Endpoints() {
        String baseUrl = "https://whitesmokehouse.com";
        RestAssured.baseURI = baseUrl;
    }

    public Response registerUser(String bodyRequest){
        Response responseRegisterUser = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/api/register");
        
        return responseRegisterUser;
    }

    public Response loginUser(String bodyRequest) {
        Response responseLoginUser = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/api/login");
        
        return responseLoginUser;
    }

    public Response getListAllObjects(String token) {
        Response responseGetListAllObjects = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/api/objects");
        
        return responseGetListAllObjects;
    }

    public Response addObject(String token, String bodyRequest) {
        Response responseAddObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/api/objects");
        
        return responseAddObject;
    }

    public Response getSingleObject(String token, int id) {
        Response responseGetSingleObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + id);
        
        return responseGetSingleObject;
    }

    public Response getListOfObjectsByIds(String token, int id) {
        
        Response responseGetListOfObjectsByIds = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/api/objectslistId?id=" + id);
        
        return responseGetListOfObjectsByIds;
    }

    public Response updateObject(String token, int id, String bodyRequest) {
        
        Response responseUpdateObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyRequest)
                .log().all()
                .when()
                .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + id);
        
        return responseUpdateObject;
    }

    public Response partiallyUpdateObject(String token, int id, String bodyRequest) {
        
        Response responsePartiallyUpdateObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyRequest)
                .log().all()
                .when()
                .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/" + id);
        
        return responsePartiallyUpdateObject;
    }

    public Response deleteObject(String token, int id) {
        
        Response responseDeleteObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + id);
        
        return responseDeleteObject;
    }

    public Response getAllDepartments(String token) {
        
        Response responseGetAllDepartments = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/api/department");
        
        return responseGetAllDepartments;
    }

    public Response cucumberEndpoints(String method, String url, String body) {

        switch (url) {
            case "update-url":
                url = "/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + ObjectDefinition.objectId;
                break;
            case "delete-url":
                url = "/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + ObjectDefinition.objectId;
                break;
            default:
                break;
        }

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", ObjectDefinition.token != null && !ObjectDefinition.token.isEmpty() ? "Bearer " + ObjectDefinition.token : "")
                .body(body != null ? body : "")
                .log().all()
                .when()
                .request(method, url);

        return response;
    }
}
