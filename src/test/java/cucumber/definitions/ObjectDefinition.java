package cucumber.definitions;

import java.util.List;

import org.testng.Assert;

import com.apiautomation.model.ResponseObject;
import com.apiautomation.model.ResponseUpdateObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import apiengine.Endpoints;
import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ObjectDefinition extends Endpoints{
    public static Response response;
    public static String token;
    public static Integer objectId;
    private final TestContext context;

    public ObjectDefinition(TestContext context) {
        this.context = context;
    }

    @When("Send a http {string} request to {string} with body:")
    public void send_post_request(String method, String url, String body) {
        response = cucumberEndpoints(method, url, body);
        context.setResponse(response);
    }

    @Then("The response status must be {int}")
    public void send_request_http(int statusCode) {
        assert context.getResponse().statusCode() == statusCode : "Error, due to actual status code is " + context.getResponse().statusCode();
    }

    @And("Save the token from the response to local storage")
    public void save_the_token() {
        ObjectDefinition.token = response.jsonPath().getString("token");
    }

    @Given("Make sure token in local storage not empty")
    public void assert_token_in_variable() {
        assert ObjectDefinition.token != null : "Token null";
    }

    @And("Save the objectId from the response to local storage")
    public void save_the_object_id() {
        ObjectDefinition.objectId = response.jsonPath().getInt("[0].id");
    }

    @And("name in the response must be {string}")
    public void assert_name(String name) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseObject> responseObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseObject>>() {});
        Assert.assertEquals(responseObjects.get(0).getName(), name, "Object name is not as expected");
    }

    @And("year in the response must be {int}")
    public void assert_year(int year) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseObject> responseObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseObject>>() {});
 
        ResponseObject.DataObject data = responseObjects.get(0).getData();
        Assert.assertEquals(data.getYear(), year, "Object year is not as expected");
    }

    @And("price in the response must be {double}")
    public void assert_price(double price) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseObject> responseObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseObject>>() {});
 
        ResponseObject.DataObject data = responseObjects.get(0).getData();
        Assert.assertEquals(data.getPrice(), price, "Object price is not as expected");
    }

    @And("cpu_model in the response must be {string}")
    public void assert_cpu_model(String cpuModel) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseObject> responseObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseObject>>() {});
 
        ResponseObject.DataObject data = responseObjects.get(0).getData();
        Assert.assertEquals(data.getCpu_model(), cpuModel, "Object cpu model is not as expected");
    }

    @And("hard_disk_size in the response must be {string}")
    public void assert_hard_disk_size(String hardDiskSize) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseObject> responseObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseObject>>() {});
 
        ResponseObject.DataObject data = responseObjects.get(0).getData();
        Assert.assertEquals(data.getHard_disk_size(), hardDiskSize, "Object hard disk size is not as expected");
    }

    @And("capacity in the response must be {string}")
    public void assert_capacity(String capacity) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseObject> responseObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseObject>>() {});
 
        ResponseObject.DataObject data = responseObjects.get(0).getData();
        Assert.assertEquals(data.getCapacity(), capacity, "Object capacity is not as expected");
    }

    @And("screen_size in the response must be {string}")
    public void assert_screen_size(String screenSize) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseObject> responseObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseObject>>() {});
 
        ResponseObject.DataObject data = responseObjects.get(0).getData();
        Assert.assertEquals(data.getScreen_size(), screenSize, "Object screen_size is not as expected");
    }

    @And("color in the response must be {string}")
    public void assert_color(String color) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseObject> responseObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseObject>>() {});
 
        ResponseObject.DataObject data = responseObjects.get(0).getData();
        Assert.assertEquals(data.getColor(), color, "Object color is not as expected");
    }

    @And("name update in the response must be {string}")
    public void assert_name_update(String name) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseUpdateObject> responseUpdateObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseUpdateObject>>() {});
        Assert.assertEquals(responseUpdateObjects.get(0).getName(), name, "Object name is not as expected");
    }

    @And("year update in the response must be {int}")
    public void assert_year_update(int year) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseUpdateObject> responseUpdateObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseUpdateObject>>() {});
 
        ResponseUpdateObject.DataObject data = responseUpdateObjects.get(0).getData();
        Assert.assertEquals(data.getYear(), year, "Object year is not as expected");
    }

    @And("price update in the response must be {double}")
    public void assert_price_update(double price) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseUpdateObject> responseUpdateObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseUpdateObject>>() {});
 
        ResponseUpdateObject.DataObject data = responseUpdateObjects.get(0).getData();
        Assert.assertEquals(data.getPrice(), price, "Object price is not as expected");
    }

    @And("cpu_model update in the response must be {string}")
    public void assert_cpu_model_update(String cpuModel) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseUpdateObject> responseUpdateObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseUpdateObject>>() {});
 
        ResponseUpdateObject.DataObject data = responseUpdateObjects.get(0).getData();
        Assert.assertEquals(data.getCpu_model(), cpuModel, "Object cpu model is not as expected");
    }

    @And("hard_disk_size update in the response must be {string}")
    public void assert_hard_disk_size_update(String hardDiskSize) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseUpdateObject> responseUpdateObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseUpdateObject>>() {});
 
        ResponseUpdateObject.DataObject data = responseUpdateObjects.get(0).getData();
        Assert.assertEquals(data.getHard_disk_size(), hardDiskSize, "Object hard disk size is not as expected");
    }

    @And("capacity update in the response must be {string}")
    public void assert_capacity_update(String capacity) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseUpdateObject> responseUpdateObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseUpdateObject>>() {});
 
        ResponseUpdateObject.DataObject data = responseUpdateObjects.get(0).getData();
        Assert.assertEquals(data.getCapacity(), capacity, "Object capacity is not as expected");
    }

    @And("screen_size update in the response must be {string}")
    public void assert_screen_size_update(String screenSize) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseUpdateObject> responseUpdateObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseUpdateObject>>() {});
 
        ResponseUpdateObject.DataObject data = responseUpdateObjects.get(0).getData();
        Assert.assertEquals(data.getScreen_size(), screenSize, "Object screen size is not as expected");
    }

    @And("color update in the response must be {string}")
    public void assert_color_update(String color) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseUpdateObject> responseUpdateObjects = objectMapper.readValue(response.body().asString(), new TypeReference<List<ResponseUpdateObject>>() {});
 
        ResponseUpdateObject.DataObject data = responseUpdateObjects.get(0).getData();
        Assert.assertEquals(data.getColor(), color, "Object color is not as expected");
    }
}