package utils;

public class RequestPayloads {

    public static String getRegisterPayload(String uniqueEmail) {
        return "{\n" +
                "  \"email\": \"" + uniqueEmail + "\",\n" +
                "  \"full_name\": \"Daffa Virdianto\",\n" +
                "  \"password\": \"D@ffa123\",\n" +
                "  \"department\": \"Human Resource\",\n" +
                "  \"phone_number\": \"081325018827\"\n" +
                "}";
    }

    public static String getLoginPayload() {
        return "{\n" +
                "  \"email\": \"daffa.virdianto1@gmail.com\",\n" +
                "  \"password\": \"D@ffa123\"\n" +
                "}";
    }

    public static String getAddObjectPayload() {
        return "{\n" +
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
    }

    public static String updateObjectPayload() {
        return "{\n" +
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
    }
    

    public static String partiallyUpdateObject() {
        return "{\n" +
            "  \"name\": \"Apple MacBook Pro 1611-daffa\",\n" +
            "  \"year\": \"2030\"\n" +
            "}";
    }

}
