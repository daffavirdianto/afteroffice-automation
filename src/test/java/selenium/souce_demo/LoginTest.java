package selenium.souce_demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;

    /*
     * Login Test Case:
     * 1. Valid credential login = standard_user, secret_sauce
     * 2. Epic sadface: Sorry, this user has been locked out. = locked_out_user, secret_sauce
     * 3. Valid Email, empty Password = standard_user, Epic sadface: Password is required
     * 4. empty Email, valid Password = secret_sauce, Epic sadface: Username is required
     */
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Daffa Virdianto\\afteroffice-automation\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validLoginCredentials() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);

        String ProductsTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();

        Assert.assertEquals(ProductsTitle, "Products", "Product title does not match!");
    }

    @Test(dataProvider = "invalidCredentialsData")
    public void invalidLoginCredentials(String username, String password, String expectedErrorMessage) throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(2000);
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();

        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message does not match!");

    }

    @DataProvider(name = "invalidCredentialsData")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
            {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
            {"standard_user", "", "Epic sadface: Password is required"},
            {"", "secret_sauce", "Epic sadface: Username is required"}
        };
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
