package com.afteroffice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    By errorMessage = By.xpath("//h3[@data-test='error']");

    public void login(String username, String password) {
        usernameInput.sendKeys(null == username ? "" : username);
        passwordInput.sendKeys(null == password ? "" : password);
        loginButton.click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
