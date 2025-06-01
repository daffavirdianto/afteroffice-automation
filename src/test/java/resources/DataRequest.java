package resources;

import java.util.HashMap;
import java.util.Map;

public class DataRequest {

    public Map<String, String> addRegisterCollection(String uniqueEmail) {
        Map<String, String> request = new HashMap<>();

        request.put("addRegister", "{\n" +
                "  \"email\": \"" + uniqueEmail + "\",\n" +
                "  \"full_name\": \"Daffa Virdianto\",\n" +
                "  \"password\": \"D@ffa123\",\n" +
                "  \"department\": \"Human Resource\",\n" +
                "  \"phone_number\": \"081325018827\"\n" +
                "}");

        request.put("addRegisterFailed", "{\n" +
                "  \"email\": \",\n" +
                "  \"full_name\": \"Daffa Virdianto\",\n" +
                "  \"password\": \"D@ffa123\",\n" +
                "  \"department\": \"Human Resource\",\n" +
                "  \"phone_number\": \"081325018827\"\n" +
                "}");
        
        return request;
    }

    public Map<String, String> addLoginCollection() {
        Map<String, String> request = new HashMap<>();

        request.put("addLogin", "{\n" +
                "  \"email\": \"daffa.virdianto1@gmail.com\",\n" +
                "  \"password\": \"D@ffa123\"\n" +
                "}");
        
        request.put("addLoginFailed", "{\n" +
                "  \"email\": \",\n" +
                "  \"password\": \"D@ffa123\"\n" +
                "}");

        return request;
    }

    public Map<String, String> addCreateObjectCollection() {
        Map<String, String> request = new HashMap<>();

        request.put("addCreateObject", "{\n" +
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
                "}");

        return request;
    }
}
