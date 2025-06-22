package com.afteroffice.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.afteroffice.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebElement;

public class ProductPage extends AbstractComponent {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement cartButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateButton;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement crossButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    By listOfProducts = By.className("inventory_item");
    By addCartButton = By.xpath("//button[contains(@data-test,'add-to-cart')]");
    By productTitle = By.xpath("//span[@class='title']");
    By filterDropdown = By.cssSelector("select.product_sort_container");
    By lowToHighOption = By.cssSelector("option[value='lohi']");
    By highToLowOption = By.cssSelector("option[value='hilo']");
    By aToZOption = By.cssSelector("option[value='az']");
    By zToAOption = By.cssSelector("option[value='za']");
    By priceProduct = By.className("inventory_item_price");
    By nameProduct = By.className("inventory_item_name");

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    public int getProductIndexByName(String productName) throws InterruptedException {
        visibilityOfElementLocated(listOfProducts);

        List<WebElement> products = driver.findElements(listOfProducts);
        for (int i = 0; i < products.size(); i++) {
            WebElement nameElement = products.get(i).findElement(nameProduct);
            if (nameElement.getText().equals(productName)) {
                System.out.println("Product '" + productName + "' found at index: " + i);
                return i;
            }
        }
        System.out.println("Product '" + productName + "' not found.");
        return -1;
    }

    public void addToCart(String productName) throws InterruptedException {
        visibilityOfElementLocated(listOfProducts);
        List<WebElement> addToCartButtons = driver.findElements(addCartButton);
        int productIndex = getProductIndexByName(productName);
        addToCartButtons.get(productIndex).click();
        Thread.sleep(2000);
    }

    public void addToMultipleProductsToCart(List<String> productNames) throws InterruptedException {
        visibilityOfElementLocated(listOfProducts);
        List<WebElement> addToCartButtons = driver.findElements(addCartButton);

        for (String productName : productNames) {
            int productIndex = getProductIndexByName(productName);
            if (productIndex != -1) {
                addToCartButtons.get(productIndex).click();
                Thread.sleep(2000);
            } else {
                System.out.println("Product '" + productName + "' not found.");
            }
        }
    }

    public void clickOnCartButton() {
        cartButton.click();
    }

    public boolean isProductsSortedByPriceLowToHigh() {
        visibilityOfElementLocated(priceProduct);

        List<Double> prices = driver.findElements(priceProduct)
                .stream()
                .map(e -> e.getText().replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Price Low To High : " + prices);

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isProductsSortedByPriceHighToLow() {
        visibilityOfElementLocated(priceProduct);

        List<Double> prices = driver.findElements(priceProduct)
                .stream()
                .map(e -> e.getText().replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Price High To Low : " + prices);

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean isProductsSortedByNameAToZ() {
        visibilityOfElementLocated(priceProduct);

        List<String> productNames = driver.findElements(nameProduct)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());

        System.out.println("Product Names A to Z : " + productNames);

        List<String> sortedNames = productNames.stream().sorted().collect(Collectors.toList());
        return productNames.equals(sortedNames);
    }

    public boolean isProductsSortedByNameZToA() {
        visibilityOfElementLocated(priceProduct);

        List<String> productNames = driver.findElements(nameProduct)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());

        System.out.println("Product Names Z to A : " + productNames);

        List<String> sortedNames = productNames.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        return productNames.equals(sortedNames);
    }

    public void selectLowToHighOption() throws InterruptedException {
        driver.findElement(filterDropdown).click();
        driver.findElement(lowToHighOption).click();
        Thread.sleep(2000);
    }

    public void selectHighToLowOption() throws InterruptedException {
        driver.findElement(filterDropdown).click();
        driver.findElement(highToLowOption).click();
        Thread.sleep(2000);
    }

    public void selectAToZOption() throws InterruptedException {
        driver.findElement(filterDropdown).click();
        driver.findElement(aToZOption).click();
        Thread.sleep(2000);
    }

    public void selectZToAOption() throws InterruptedException {
        driver.findElement(filterDropdown).click();
        driver.findElement(zToAOption).click();
        Thread.sleep(2000);
    }

    public void clickOnMenuButton() {
        menuButton.click();
    }

    public void resetAppState() {
        resetAppStateButton.click();
    }

    public void clickOnCrossButton() {
        crossButton.click();
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

}
