package com.afteroffice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompletePage {

    WebDriver driver;
    public CompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    public String getCompleteMessage() {
        return driver.findElement(By.className("complete-header")).getText();
    }

    public void clickBackToProducts() {
        backToProductsButton.click();
    }

}
