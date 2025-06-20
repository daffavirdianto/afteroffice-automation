package selenium.rahulshettyacademy.pom;

import org.testng.annotations.Test;

import com.afteroffice.base.BaseTest;
import com.afteroffice.pageobjects.rahulshettyacademy.CartPage;
import com.afteroffice.pageobjects.rahulshettyacademy.ConfirmationPage;
import com.afteroffice.pageobjects.rahulshettyacademy.DashboardPage;
import com.afteroffice.pageobjects.rahulshettyacademy.LoginPage;
import com.afteroffice.pageobjects.rahulshettyacademy.OrderPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutTest extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartPage cartPage;
    OrderPage orderPage;
    ConfirmationPage confirmationPage;

    @BeforeClass
    public void setup() throws InterruptedException{
        // Setup WebDriver
        super.setUp();

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }

    @Test
    public void Login(){
        System.out.println("Valid credentials test is running.");
        loginPage.loginApplication("simanjuntakalbert57@gmail.com", "XBf@rWNvByn!#K8");
        Assert.assertEquals(dashboardPage.getHomePageText(), "Automation Practice","Home page text does not match!");
    }

    @Test(dependsOnMethods = {"Login"})
    public void CheckoutScenarioTest() throws InterruptedException{

        // DashboardPage
        String productName = "ZARA COAT 3";
        dashboardPage.addToCart(productName);
        dashboardPage.clickOnCart();

        // Scenario Cart Page
        Assert.assertTrue(cartPage.verifyCheckoutProduct(productName), "Product not found in cart!");
        cartPage.goToCheckoutPage();

        // Scenario select address
        orderPage.selectCountry("Indonesia");
        orderPage.submitOrder();

        // Scenario Order Confirmation Page
        Assert.assertTrue(confirmationPage.getConfirmationMessage().contains("THANKYOU FOR THE ORDER."), "Order confirmation message not found!");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        super.tearDown();
    }

}
