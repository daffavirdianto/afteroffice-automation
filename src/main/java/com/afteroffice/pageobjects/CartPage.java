package com.afteroffice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.afteroffice.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingBtn;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    By listOfProducts = By.className("cart_item");

    @FindBy(xpath = "//div[@class='item_pricebar']/button")
    private List<WebElement> removeProductButton;

    By nameProduct = By.className("inventory_item_name");

    public void goToCheckoutPage() {
        checkoutButton.click();
    }

    public void continueShopping() {
        continueShoppingBtn.click();
    }

    public boolean verifyCheckoutProduct(String productName) {
        List<WebElement> products = driver.findElements(nameProduct);
        if (products.isEmpty()) {
            return false;
        }
        for (WebElement product : products) {
            if (product.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public Boolean verifyMultipleCheckoutProduct(List<String> productNames) {
        visibilityOfElementLocated(nameProduct);
        List<WebElement> products = driver.findElements(listOfProducts);
        return products.stream()
                .anyMatch(cartProd -> productNames.stream()
                        .anyMatch(name -> cartProd.findElement(nameProduct).getText().equals(name)));
    }

    public void removeProductFromCart(String productName) {
        visibilityOfElementLocated(nameProduct);
        List<WebElement> products = driver.findElements(listOfProducts);
        WebElement productToRemove = products.stream()
                .filter(prod -> prod.findElement(nameProduct).getText().equals(productName)).findFirst().orElse(null);
        if (productToRemove != null) {
            productToRemove.findElement(By.xpath(".//button[text()='Remove']")).click();
        }
    }

    public Boolean isItemCartVisible(){
       return isElementPresent(listOfProducts);
    }
}
