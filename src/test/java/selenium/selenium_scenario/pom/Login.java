package selenium.selenium_scenario.pom;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.afteroffice.base.BaseTest;
import com.afteroffice.pageobjects.DashboardPage;
import com.afteroffice.pageobjects.LoginPage;

public class Login extends BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setup() throws InterruptedException{
        // Setup WebDriver
        super.setUp();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test(priority = 1)
    public void validCredentials() throws InterruptedException {

        System.out.println("Valid credentials test is running.");

        loginPage.loginApplication("simanjuntakalbert57@gmail.com", "XBf@rWNvByn!#K8");
        Assert.assertEquals(dashboardPage.getHomePageText(), "Automation Practice","Home page text does not match!");
    }

    @Test(priority = 1, dataProvider = "invalidCredentialsData")
    public void invalidCredentials(String email, String password, String emailError, String passwordError) throws InterruptedException {

        System.out.println("Invalid credentials test is running.");

        /*
         * 1. Valid Email , Invalid Password
         * 2. Invalid Email , Valid Password
         * 3. Invalid Email , Invalid Password
         * 4. Empty Email , Invalid Password
         */
        loginPage.loginApplication(email, password);

        if(loginPage.isEmailErrorMessageVisible()) {
            String emailErrorMessage = loginPage.getEmailErrorMessage();
            Assert.assertEquals(emailErrorMessage, emailError, "Email error message does not match!");
        }

        if (loginPage.isPasswordErrorMessageVisible()) {
            String passwordErrorMessage = loginPage.getPasswordErrorMessage();        
            Assert.assertEquals(passwordErrorMessage, passwordError, "Password error message does not match!");
        }
    }

    @DataProvider(name = "invalidCredentialsData")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
            {"simanjuntakalbert57@gmail.com","","","*Password is required"},
            {"simanjuntakalbert57","XBf@rWNvByn!#K8","*Enter Valid Email",""},
            {"simanjuntakalbert57","","*Enter Valid Email","*Password is required"},
            {"","vByn!#K8","*Email is required",""},
            {"","", "*Email is required", "*Password is required"}
        };
    }

    @AfterMethod
    public void tearDown() {
        super.tearDown();
    }

}
