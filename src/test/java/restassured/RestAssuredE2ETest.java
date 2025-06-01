package restassured;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.apiautomation.model.ResponseObject;
import com.apiautomation.model.ResponseCreateObject;
import com.apiautomation.model.ResponseUpdateObject;
import com.apiautomation.model.request.RequestCreateObject;
import com.apiautomation.model.request.RequestLogin;
import com.apiautomation.model.request.RequestObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredE2ETest {

        String tokenLogin;

        Integer objectId;

        @BeforeClass
        public void setup() {

                RestAssured.baseURI = "https://whitesmokehouse.com";

                RequestLogin requestLogin = new RequestLogin();
                requestLogin.setEmail("daffa.virdianto1@gmail.com");
                requestLogin.setPassword("D@ffa123");

                Response responseLogin = RestAssured.given()
                                .header("Content-Type", "application/json")
                                .body(requestLogin)
                                .log().all()
                                .when()
                                .post("/webhook/api/login");

                tokenLogin = responseLogin.jsonPath().getString("token");
                Assert.assertNotNull(tokenLogin, "Token should not be null");
        }

        /*
         * Test Case : 001 : Create Object
         * 1. Login
         * 2. AddObject
         * 3. SingleObject
         */
        @Test(priority = 1)
        public void createObject() throws JsonProcessingException {

                RequestCreateObject requestCreateObject = new RequestCreateObject("Apple MacBook Pro 16",
                                new RequestCreateObject.DataObject(
                                                2019, 1849.99, "Intel Core i9", "1 TB", "2 cpu",
                                                "14 Inch", "red"));

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonCreate = objectMapper.writeValueAsString(requestCreateObject);

                Response responseAddObject = RestAssured.given()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + tokenLogin)
                                .body(jsonCreate)
                                .log().all()
                                .when()
                                .post("/webhook/api/objects");

                objectId = responseAddObject.jsonPath().getInt("[0].id");
                System.out.println("Response: " + responseAddObject.asPrettyString());

                ResponseCreateObject[] response = responseAddObject.as(ResponseCreateObject[].class);
                Assert.assertEquals(responseAddObject.getStatusCode(), 200, "Status code is not 200");

                Assert.assertEquals(response[0].name, "Apple MacBook Pro 16", "Object name is not as expected");
                Assert.assertEquals(response[0].data.year, 2019, "Object year is not as expected");
                Assert.assertEquals(response[0].data.price, 1849.99, "Object price is not as expected");
                Assert.assertEquals(response[0].data.cpu_model, "Intel Core i9", "Object CPU model is not as expected");
                Assert.assertEquals(response[0].data.hard_disk_size, "1 TB", "Object hard disk size is not as expected");
                Assert.assertEquals(response[0].data.capacity, "2 cpu", "Object capacity is not as expected");
                Assert.assertEquals(response[0].data.screen_size, "14 Inch", "Object screen size is not as expected");
                Assert.assertEquals(response[0].data.color, "red", "Object color is not as expected");

                Response responseSingleObject = RestAssured.given()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + tokenLogin)
                                .log().all()
                                .when()
                                .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + objectId);

                System.out.println("Response: " + responseSingleObject.asPrettyString());

                ResponseCreateObject responseList = responseSingleObject.as(ResponseCreateObject.class);
                Assert.assertEquals(responseSingleObject.getStatusCode(), 200, "Status code is not 200");
                Assert.assertEquals(responseList.name, "Apple MacBook Pro 16", "Object name is not as expected");
                Assert.assertEquals(responseList.data.year, 2019, "Object year is not as expected");
                Assert.assertEquals(responseList.data.price, 1849.99, "Object price is not as expected");
                Assert.assertEquals(responseList.data.cpu_model, "Intel Core i9", "Object CPU model is not as expected");
                Assert.assertEquals(responseList.data.hard_disk_size, "1 TB", "Object hard disk size is not as expected");
                Assert.assertEquals(responseList.data.capacity, "2", "Object capacity is not as expected");
                Assert.assertEquals(responseList.data.screen_size, "14", "Object screen size is not as expected");
                Assert.assertEquals(responseList.data.color, "red", "Object color is not as expected");
        }

        /*
         * Test Case : 002 : Update Object
         * 1. Login
         * 2. AddObject
         * 3. UpdateObject
         * 4. ListOfObjectsByIds
         * 
         * With Lombok
         */
        @Test(priority = 2)
        public void updateObject() {
                RequestObject requestAddObject = new RequestObject();
                requestAddObject.setName("Apple MacBook Pro 16");

                RequestObject.DataObject dataObject = new RequestObject.DataObject();
                dataObject.setYear(2019);
                dataObject.setPrice(1849.99);
                dataObject.setCpu_model("Intel Core i9");
                dataObject.setHard_disk_size("1 TB");
                dataObject.setCapacity("2 cpu");
                dataObject.setScreen_size("14 Inch");
                dataObject.setColor("red");
                requestAddObject.setData(dataObject);

                Response responseAddObject = RestAssured.given()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + tokenLogin)
                                .body(requestAddObject)
                                .log().all()
                                .when()
                                .post("/webhook/api/objects");

                objectId = responseAddObject.jsonPath().getInt("[0].id");
                ResponseObject[] responseObject = responseAddObject.as(ResponseObject[].class);
                Assert.assertEquals(responseObject[0].getName(), "Apple MacBook Pro 16",
                                "Object name is not as expected");
                Assert.assertEquals(responseObject[0].getData().getYear(), 2019,
                                "Object year is not as expected");
                Assert.assertEquals(responseObject[0].getData().getPrice(), 1849.99,
                                "Object price is not as expected");
                Assert.assertEquals(responseObject[0].getData().getCpu_model(), "Intel Core i9",
                                "Object CPU model is not as expected");
                Assert.assertEquals(responseObject[0].getData().getHard_disk_size(), "1 TB",
                                "Object hard disk size is not as expected");
                Assert.assertEquals(responseObject[0].getData().getCapacity(), "2 cpu",
                                "Object capacity is not as expected");
                Assert.assertEquals(responseObject[0].getData().getScreen_size(), "14 Inch",
                                "Object screen size is not as expected");
                Assert.assertEquals(responseObject[0].getData().getColor(), "red",
                                "Object color is not as expected");

                RequestObject requestUpdateObject = new RequestObject();
                requestUpdateObject.setName("Apple MacBook Pro 18");

                RequestObject.DataObject dataUpdateObject = new RequestObject.DataObject();
                dataUpdateObject.setYear(2019);
                dataUpdateObject.setPrice(1849.99);
                dataUpdateObject.setCpu_model("Intel Core i9");
                dataUpdateObject.setHard_disk_size("1 TB");
                dataUpdateObject.setCapacity("2 cpu");
                dataUpdateObject.setScreen_size("14 Inch");
                dataUpdateObject.setColor("blue");
                requestUpdateObject.setData(dataUpdateObject);

                Response responseUpdateObject = RestAssured.given()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + tokenLogin)
                                .body(requestUpdateObject)
                                .log().all()
                                .when()
                                .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + objectId);

                System.out.println("Response: " + responseUpdateObject.asPrettyString());
                ResponseUpdateObject[] responseUpdateObjects = responseUpdateObject.as(ResponseUpdateObject[].class);

                Assert.assertEquals(responseUpdateObjects[0].getName(), "Apple MacBook Pro 18",
                                "Object name is not as expected");
                Assert.assertEquals(responseUpdateObjects[0].getData().getYear(), 2019,
                                "Object year is not as expected");
                Assert.assertEquals(responseUpdateObjects[0].getData().getPrice(), 1849.99,
                                "Object price is not as expected");
                Assert.assertEquals(responseUpdateObjects[0].getData().getCpu_model(), "Intel Core i9",
                                "Object CPU model is not as expected");
                Assert.assertEquals(responseUpdateObjects[0].getData().getHard_disk_size(), "1 TB",
                                "Object hard disk size is not as expected");
                Assert.assertEquals(responseUpdateObjects[0].getData().getCapacity(), "2 cpu",
                                "Object capacity is not as expected");
                Assert.assertEquals(responseUpdateObjects[0].getData().getScreen_size(), "14 Inch",
                                "Object screen size is not as expected");
                Assert.assertEquals(responseUpdateObjects[0].getData().getColor(), "blue",
                                "Object color is not as expected");

                Response response = RestAssured.given()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + tokenLogin)
                                .params("id", objectId)
                                .log().all()
                                .when()
                                .get("/webhook/api/objectslistId?id=" + objectId);

                System.out.println("Response: " + response.asPrettyString());

                ResponseObject[] responseList = response.as(ResponseObject[].class);
                Assert.assertEquals(responseList[0].getName(), "Apple MacBook Pro 18",
                                "Object name is not as expected");
                Assert.assertEquals(responseList[0].getData().getYear(), 2019,
                                "Object year is not as expected");
                Assert.assertEquals(responseList[0].getData().getPrice(), 1849.99,
                                "Object price is not as expected");
                Assert.assertEquals(responseList[0].getData().getCpu_model(), "Intel Core i9",
                                "Object CPU model is not as expected");
                Assert.assertEquals(responseList[0].getData().getHard_disk_size(), "1 TB",
                                "Object hard disk size is not as expected");
                Assert.assertEquals(responseList[0].getData().getCapacity(), "2",
                                "Object capacity is not as expected");
                Assert.assertEquals(responseList[0].getData().getScreen_size(), "14",
                                "Object screen size is not as expected");
                Assert.assertEquals(responseList[0].getData().getColor(), "blue",
                                "Object color is not as expected");
        }

        @AfterMethod
        public void deleteObject() {

                Response responseDeleteObject = RestAssured.given()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + tokenLogin)
                                .log().all()
                                .when()
                                .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + objectId);

                System.out.println("Response: " + responseDeleteObject.asPrettyString());
        }
}
