package com.afteroffice.pageobjects.rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.afteroffice.abstractcomponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        // Constructor for LoginPage
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // initialize the elements
    }

    // not using @FindBy for the driver, as it is not a WebElement but a WebDriver
    // instance
    // public void loginApplication(String email, String password) {
    // driver.findElement(By.id("userEmail")).sendKeys(email);
    // driver.findElement(By.id("userPassword")).sendKeys(password);
    // driver.findElement(By.id("login")).click();
    // }

    @FindBy(id = "userEmail")
    public WebElement userEmail;

    @FindBy(id = "userPassword")
    public WebElement userPassword;

    @FindBy(id = "login")
    public WebElement loginButton;

    By emailErrorMessage = By.xpath("//input[@id='userEmail']/following-sibling::div//div[@class='ng-star-inserted']");
    By passwordErrorMessage = By.xpath("//input[@id='userPassword']/following-sibling::div//div[@class='ng-star-inserted']");

    public void loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
    }

    public Boolean isEmailErrorMessageVisible(){
       return isElementPresent(emailErrorMessage);
    }

    public Boolean isPasswordErrorMessageVisible(){
       return isElementPresent(passwordErrorMessage);
    }

    public String getEmailErrorMessage(){
        return driver.findElement(emailErrorMessage).getText();
    }

    public String getPasswordErrorMessage(){
        return driver.findElement(passwordErrorMessage).getText();
    }
}
