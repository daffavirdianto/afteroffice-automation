package selenium.souce_demo;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.afteroffice.base.BaseTest;
import com.afteroffice.pageobjects.LoginPage;
import com.afteroffice.pageobjects.ProductPage;

import helper.ConfigManager;

public class FilteringTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;

    @BeforeClass
    public void setUp() {
        String demoUrl = ConfigManager.getDemoUrl();
        super.setUp(demoUrl);

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
    }

    /*
     * Filtering Products Test Case:
     * 1. Filter products by price (low to high).
     * 2. Filter products by price (high to low).
     * 3. Filter products by name (A to Z).
     * 4. Filter products by name (Z to A).
     */
    @Test
    public void validLoginCredentials() throws InterruptedException {
        System.out.println("Login Valid Credentials Test Started");
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productPage.getProductTitle(), "Products", "Product title does not match!");
    }

    @Test(priority = 1, dependsOnMethods = { "validLoginCredentials" })
    public void filterProductsByPriceLowToHigh() throws InterruptedException {
        System.out.println("Filtering Products by Price Low to High Test Started");
        productPage.selectLowToHighOption();
        Assert.assertTrue(productPage.isProductsSortedByPriceLowToHigh(), "Products are not sorted by price in ascending order");
    }

    @Test(priority = 2, dependsOnMethods = { "validLoginCredentials" })
    public void filterProductsByPriceHighToLow() throws InterruptedException {
        System.out.println("Filtering Products by Price High to Low Test Started");
        productPage.selectHighToLowOption();
        Assert.assertTrue(productPage.isProductsSortedByPriceHighToLow(), "Products are not sorted by price in descending order");
    }

    @Test(priority = 3, dependsOnMethods = { "validLoginCredentials" })
    public void filterProductsByNameAToZ() throws InterruptedException {
        System.out.println("Filtering Products by Name A to Z Test Started");
        productPage.selectAToZOption();
        Assert.assertTrue(productPage.isProductsSortedByNameAToZ(), "Products are not sorted by name in A to Z order");
    }

    @Test(priority = 4, dependsOnMethods = { "validLoginCredentials" })
    public void filterProductsByNameZToA() throws InterruptedException {
        System.out.println("Filtering Products by Name Z to A Test Started");
        productPage.selectZToAOption();
        Assert.assertTrue(productPage.isProductsSortedByNameZToA(), "Products are not sorted by name in Z to A order");
    }

    @AfterClass
    public void tearDown() {
        super.tearDown();
    }
}
