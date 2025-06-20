package apiengine;

import org.testng.Assert;

import com.apiautomation.model.ResponseCreateObject;
import com.apiautomation.model.request.RequestCreateObject;

public class Assertion {

    public void assertCreateObject(ResponseCreateObject responseCreateObject, RequestCreateObject requestCreateObject) {
        Assert.assertEquals(responseCreateObject.name, requestCreateObject.name, "Name is not as expected");
        Assert.assertEquals(responseCreateObject.data.year, requestCreateObject.data.year, "Year is not as expected");
        Assert.assertEquals(responseCreateObject.data.price, requestCreateObject.data.price, "Price is not as expected");
        Assert.assertEquals(responseCreateObject.data.cpu_model, requestCreateObject.data.cpu_model, "CPU model is not as expected");
        Assert.assertEquals(responseCreateObject.data.hard_disk_size, requestCreateObject.data.hard_disk_size, "Hard disk size is not as expected");
        Assert.assertEquals(responseCreateObject.data.capacity, requestCreateObject.data.capacity, "Capacity is not as expected");
        Assert.assertEquals(responseCreateObject.data.screen_size, requestCreateObject.data.screen_size, "Screen size is not as expected");
    }

    
}
