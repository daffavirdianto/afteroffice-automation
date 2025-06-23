package selenium.definitions;

import org.testng.Assert;

import com.afteroffice.base.BaseTest;
import com.afteroffice.pageobjects.CartPage;
import com.afteroffice.pageobjects.CheckoutPage;
import com.afteroffice.pageobjects.CompletePage;
import com.afteroffice.pageobjects.LoginPage;
import com.afteroffice.pageobjects.OverviewPage;
import com.afteroffice.pageobjects.ProductPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.hook.Hooks;

public class StepDefinitions extends BaseTest{

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OverviewPage overviewPage;
    CompletePage completePage;

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver = Hooks.initializeDriver();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        overviewPage = new OverviewPage(driver);
        checkoutPage = new CheckoutPage(driver);
        completePage = new CompletePage(driver);
    }

    @When("User inputs email {string} and password {string}")
    public void userInputsEmailAndPassword(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("User should be redirected to the products page")
    public void verifyRedirectionToProductsPage() {
        Assert.assertEquals(productPage.getProductTitle(), "Products", "Product title does not match!");
    }

    @Then("Verify error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Error message does not match!");
    }

    @When("User adds item {string} to the cart")
    public void userAddsItemToCart(String productName) throws InterruptedException {
        productPage.addToCart(productName);
    }
    
    @And("User redirect to cart page")
    public void userRedirectToCartPage() {
        productPage.clickOnCartButton();
    }
    
    @Then("User should see {string} in the cart")
    public void userShouldSeeItemInCart(String productName) {
        Assert.assertTrue(cartPage.verifyCheckoutProduct(productName), "Product not found in cart!");
    }

    @When("User clicks on Checkout button")
    public void userClicksOnCheckoutButton() {
        cartPage.goToCheckoutPage();
    }
    
    @When("User inputs fill information with first name {string}, last name {string}, and zip code {string}")
    public void userInputsShippingInformation(String firstName, String lastName, String zipCode) {
        checkoutPage.fillShippingInformation(firstName, lastName, zipCode);
    }
    
    @And("User clicks on Continue button")
    public void userClicksOnContinueButton() {
        checkoutPage.clickContinue();
    }
    
    @When("User clicks on Finish button")
    public void userClicksOnFinishButton() {
        overviewPage.clickFinishButton();
    }
    
    @Then("User should see {string} message")
    public void userShouldSeeMessage(String expectedMessage) {
        Assert.assertEquals(completePage.getCompleteMessage(), expectedMessage,
                "Order confirmation message does not match!");
    }

    @When("User clicks on Back Home button")
    public void userClicksOnBackToProductsButton() {
        completePage.clickBackToProducts();
    }
    
    @When("User selects Price low to high from the filter options")
    public void userSelectsPriceLowToHigh() throws InterruptedException {
        productPage.selectLowToHighOption();
    }

    @Then("Verify that products are sorted by price from low to high")
    public void verifyProductsSortedByPriceLowToHigh() {
        Assert.assertTrue(productPage.isProductsSortedByPriceLowToHigh(), "Products are not sorted by price in ascending order");
    }

    @When("User selects Price high to low from the filter options")
    public void userSelectsPriceHighToLow() throws InterruptedException {
        productPage.selectHighToLowOption();
    }

    @Then("Verify that products are sorted by price from high to low")
    public void verifyProductsSortedByPriceHighToLow() {
        Assert.assertTrue(productPage.isProductsSortedByPriceHighToLow(), "Products are not sorted by price in descending order");
    }

    @When("User selects Name A to Z from the filter options")
    public void userSelectsNameAToZ() throws InterruptedException {
        productPage.selectAToZOption();
    }

    @Then("Verify that products are sorted by name from A to Z")
    public void verifyProductsSortedByNameAToZ() {
        Assert.assertTrue(productPage.isProductsSortedByNameAToZ(), "Products are not sorted by name in A to Z order");
    }

    @When("User selects Name Z to A from the filter options")
    public void userSelectsNameZToA() throws InterruptedException {
        productPage.selectZToAOption();
    }

    @Then("Verify that products are sorted by name from Z to A")
    public void verifyProductsSortedByNameZToA() {
        Assert.assertTrue(productPage.isProductsSortedByNameZToA(), "Products are not sorted by name in Z to A order");
    }
}
