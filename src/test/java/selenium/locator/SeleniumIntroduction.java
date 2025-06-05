package selenium.locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumIntroduction {
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Daffa Virdianto\\apiautomation\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(5000);
    }

    @Test
    public void loginScenarioTest() throws InterruptedException {
        
        /*
         * Step 1:
         * 1. User Opens the browser and navigates to login page
         * 2. User enters username and password
         * 3. User clicks on login button
         * 4. User is redirected to the home page
         * 5. User Logs out
         */
        WebElement username = driver.findElement(By.id("inputUsername"));
        username.sendKeys("daffa.virdianto1@gmail.com");

        driver.findElement(By.name("inputPassword")).sendKeys("rahulshettyacademy");
        driver.findElement(By.className("signInBtn")).click();

        String name = driver.findElement(By.xpath("//div[@class = 'login-container']/h2")).getText();
        System.out.println("Ini adalah nama user: " + name);

        Thread.sleep(5000);
        driver.findElement(By.className("logout-btn")).click();
        Thread.sleep(5000);
    }

    @Test
    public void incorrectLoginScenarioTest() throws InterruptedException {
        /*
         * Step 2:
         * 1. User Opens the browser and navigates to login page
         * 2. User enters username and password
         * 3. User clicks on login button
         * 4. User sees error message
         */
        WebElement username = driver.findElement(By.id("inputUsername"));
        username.sendKeys("daffa.virdianto1@gmail.com");

        driver.findElement(By.className("signInBtn")).click();

        Thread.sleep(5000);

        String errorMessage = driver.findElement(By.cssSelector("p.error")).getText();
        System.out.println("Ini adalah pesan error: " + errorMessage);
    }  

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
