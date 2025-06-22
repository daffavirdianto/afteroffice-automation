package selenium.souce_demo;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.afteroffice.base.BaseTest;
import com.afteroffice.pageobjects.CartPage;
import com.afteroffice.pageobjects.CheckoutPage;
import com.afteroffice.pageobjects.CompletePage;
import com.afteroffice.pageobjects.LoginPage;
import com.afteroffice.pageobjects.OverviewPage;
import com.afteroffice.pageobjects.ProductPage;

import helper.ConfigManager;

public class CheckoutTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OverviewPage overviewPage;
    CompletePage completePage;
    String productName = "Sauce Labs Fleece Jacket";
    List<String> productNames = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light");

    @BeforeClass
    public void setUp() {
        String demoUrl = ConfigManager.getDemoUrl();
        super.setUp(demoUrl);

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
        completePage = new CompletePage(driver);
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
        System.out.println("Login Valid Credentials Test Started");
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productPage.getProductTitle(), "Products", "Product title does not match!");
    }

    @Test(priority = 1, dependsOnMethods = { "validLoginCredentials" })
    public void checkoutWithSingleProduct() throws InterruptedException {
        // Product Page
        productPage.addToCart(productName);
        productPage.clickOnCartButton();

        // Cart Page
        Assert.assertTrue(cartPage.verifyCheckoutProduct(productName), "Product not found in cart!");
        cartPage.goToCheckoutPage();

        // Fill in shipping information (Checkout)
        checkoutPage.fillShippingInformation("Daffa", "Virdianto", "12345");
        checkoutPage.clickContinue();

        // Complete the purchase (Overview)
        overviewPage.clickFinishButton();

        // Verify order confirmation page is displayed (Complete)
        Assert.assertEquals(completePage.getCompleteMessage(), "Thank you for your order!",
                "Order confirmation message does not match!");

        completePage.clickBackToProducts();
    }

    /*
     * Checkout with Multiple Products, Remove from Cart, and Reset App State Test
     * Case:
     * 1. Add multiple products to the cart.
     * 2. Proceed to checkout.
     * 3. Fill in shipping information.
     * 4. Complete the purchase.
     * 5. Verify the order confirmation page is displayed.
     */
    @Test(priority = 2, dependsOnMethods = "validLoginCredentials")
    public void checkoutWithMultipleProducts() throws InterruptedException {
        // Add multiple products to the cart
        productPage.addToMultipleProductsToCart(productNames);
        productPage.clickOnCartButton();

        // Proceed to checkout
        Assert.assertTrue(cartPage.verifyMultipleCheckoutProduct(productNames),
                "Product not found in cart!");
        cartPage.goToCheckoutPage();

        // Fill in shipping information (Checkout)
        checkoutPage.fillShippingInformation("Daffa", "Virdianto", "12345");
        checkoutPage.clickContinue();

        // Complete the purchase (Overview)
        overviewPage.clickFinishButton();

        // Verify order confirmation page is displayed (Complete)
        Assert.assertEquals(completePage.getCompleteMessage(), "Thank you for your order!",
                "Order confirmation message does not match!");

        completePage.clickBackToProducts();
    }

    /*
     * Remove from Cart and Reset App State Test Case:
     * 1. Add a product to the cart.
     * 2. Remove the product from the cart.
     * 3. Verify the cart is empty.
     * 4. Reset the app state.
     * 5. Verify the cart is empty.
     */
    @Test(priority = 3, dependsOnMethods = "validLoginCredentials")
    public void removeFromCartAndResetAppState() throws InterruptedException {
        // Add products to the cart
        productPage.addToCart(productName);
        productPage.clickOnCartButton();

        Assert.assertTrue(cartPage.verifyCheckoutProduct(productName), "Product not found in cart!");

        cartPage.removeProductFromCart(productName);
        Assert.assertFalse(cartPage.verifyCheckoutProduct(productName), "Product still found in cart after removal!");

        cartPage.continueShopping();

        productPage.addToCart(productName);
        productPage.clickOnMenuButton();
        productPage.resetAppState();
        productPage.clickOnCrossButton();
        productPage.clickOnCartButton();

        // Verify the cart is empty
        Assert.assertFalse(cartPage.isItemCartVisible(), "Cart is not empty after reset!");
        cartPage.continueShopping();
        productPage.clickOnMenuButton();
        productPage.clickOnLogoutButton();
    }

    @AfterClass
    public void tearDown() {
        super.tearDown();
    }
}
