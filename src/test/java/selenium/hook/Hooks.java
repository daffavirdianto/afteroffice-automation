package selenium.hook;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import helper.ConfigManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public static WebDriver driver;
    String demoUrl = ConfigManager.getDemoUrl();

    @Before
    public void setUp() {
        System.out.println("Setting up the test environment...");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Daffa Virdianto\\afteroffice-automation\\chromedriver.exe");
        
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get(demoUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down the test environment...");
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver initializeDriver() {
        return driver;
    }
}
