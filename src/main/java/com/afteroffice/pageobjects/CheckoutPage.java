package com.afteroffice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public void fillShippingInformation(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(null == firstName ? "" : firstName);
        lastNameInput.sendKeys(null == lastName ? "" : lastName);
        postalCodeInput.sendKeys(null == postalCode ? "" : postalCode);
    }

    public void clickContinue() {
        continueButton.click();
    }
}
