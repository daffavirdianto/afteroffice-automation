package helper;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigManager {

    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String getBaseUrl(){
        return dotenv.get("BASE_URL");
    }

    public static String getDemoUrl(){
        return dotenv.get("DEMO_URL");
    }
    
}
