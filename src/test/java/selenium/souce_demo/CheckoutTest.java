package selenium.souce_demo;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Daffa Virdianto\\apiautomation\\chromedriver.exe");
        
        ChromeOptions options = new ChromeOptions();
        
        // Disable password leak detection pop-up
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /*
     * Checkout Test Case:
     * 1. Login with valid credentials.
     * 2. Add a product to the cart.
     * 3. Proceed to checkout.
     * 4. Fill in shipping information.
     * 5. Complete the purchase.
     * 6. Verify the order confirmation page is displayed.
     */
    @Test
    public void validLoginCredentials() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        String productsTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals(productsTitle, "Products", "Product title does not match!");
    }

    @Test(priority = 1, dependsOnMethods = { "validLoginCredentials" })
    public void checkoutWithSingleProduct() throws InterruptedException {
        // Add a product to the cart
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@data-test,'add-to-cart')]"));
        System.out.println("Number of products available: " + addToCartButtons.size());

        int randomIndex = new Random().nextInt(addToCartButtons.size());
        addToCartButtons.get(randomIndex).click();
        Thread.sleep(3000);

        // Proceed to checkout
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("checkout")).click();
        Thread.sleep(3000);

        // Fill in shipping information
        driver.findElement(By.id("first-name")).sendKeys("Daffa");
        driver.findElement(By.id("last-name")).sendKeys("Virdianto");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        // Complete the purchase
        driver.findElement(By.id("finish")).click();
        Thread.sleep(3000);

        // Verify order confirmation page is displayed
        String confirmationMessage = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        Assert.assertEquals(confirmationMessage, "Thank you for your order!",
                "Order confirmation message does not match!");
        Thread.sleep(3000);

        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(2000);
    }

    /*
     * Checkout with Multiple Products, Remove from Cart, and Reset App State Test
     * Case:
     * 1. Add multiple products to the cart.
     * 2. Remove one or more products from the cart.
     * 3. Verify the removed products are no longer in the cart.
     * 4. Reset the app state.
     * 5. Verify the cart is empty.
     */
    @Test(priority = 2, dependsOnMethods = "validLoginCredentials")
    public void checkoutWithMultipleProducts() throws InterruptedException {
        // Add multiple products to the cart
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@data-test,'add-to-cart')]"));
        System.out.println("Number of products available: " + addToCartButtons.size());

        for (WebElement button : addToCartButtons) {
            button.click();
            Thread.sleep(1000);
        }

        // Proceed to checkout
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        Thread.sleep(3000);
        
        // Remove one product from the cart
        driver.findElement(By.xpath("//button[contains(@data-test,'remove-sauce-labs-backpack')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(@data-test,'remove-sauce-labs-bike-light')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("continue-shopping")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("reset_sidebar_link")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("react-burger-cross-btn")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        Thread.sleep(3000);

        // Verify the cart is empty
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItems.size(), 0, "Cart is not empty after reset!");
        Thread.sleep(2000);

        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
