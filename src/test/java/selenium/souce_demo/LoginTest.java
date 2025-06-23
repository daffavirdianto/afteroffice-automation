package selenium.souce_demo;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.afteroffice.base.BaseTest;
import com.afteroffice.pageobjects.LoginPage;
import com.afteroffice.pageobjects.ProductPage;

import helper.ConfigManager;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;

    /*
     * Login Test Case:
     * 1. Valid credential login = standard_user, secret_sauce
     * 2. Epic sadface: Sorry, this user has been locked out. = locked_out_user, secret_sauce
     * 3. Valid Email, empty Password = standard_user, Epic sadface: Password is required
     * 4. empty Email, valid Password = secret_sauce, Epic sadface: Username is required
     */
    @BeforeMethod
    public void setUp() throws InterruptedException {
        String demoUrl = ConfigManager.getDemoUrl();
        super.setUp(demoUrl);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
    }

    @Test
    public void validLoginCredentials() throws InterruptedException {
        System.out.println("Login Valid Credentials Test Started");
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productPage.getProductTitle(), "Products", "Product title does not match!");
    }

    @Test(dataProvider = "invalidCredentialsData")
    public void invalidLoginCredentials(String username, String password, String expectedErrorMessage) throws InterruptedException {
        System.out.println("Login Invalid Credentials Test Started");
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Error message does not match!");
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
        super.tearDown();
    }
}
