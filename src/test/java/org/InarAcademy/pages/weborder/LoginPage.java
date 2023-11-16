package org.InarAcademy.pages.weborder;

import org.InarAcademy.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "login-username-input")
    private WebElement usernameInput;
    @FindBy(id = "login-password-input")
    private WebElement passwordInput;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(xpath = "(//div[@role='alert'])[1]")
    private WebElement usernameAlertMessage;
    @FindBy(xpath = "(//div[@role='alert'])[last()]")
    private WebElement passwordAlertMessage;
    @FindBy(xpath = "(//h2)[1]")
    private WebElement loginScreenTitle;
    @FindBy(xpath = "(//p)[1]")
    private WebElement descriptionText;
    @FindBy(xpath = "(//p)[2]")
    private WebElement usernameInfoText;
    @FindBy(xpath = "(//p)[3]")
    private WebElement passwordInfoText;


    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get("https://inar-academy.netlify.app/weborder");
    }

    public HomePage login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public String getUsernameAlertMessage() {
        return usernameAlertMessage.getText();
    }

    public String getPasswordAlertMessage() {
        return passwordAlertMessage.getText();
    }

    public String getLoginScreenTitle() {
        return loginScreenTitle.getText();
    }

    public Boolean isUsernameInputDisplayed() {
        return usernameInput.isDisplayed();
    }

    public Boolean isPasswordInputDisplayed() {
        return passwordInput.isDisplayed();
    }

    public Boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public String getDescriptionText() {
        return descriptionText.getText();
    }

    public String getUsernameInfoText() {
        return usernameInfoText.getText();
    }

    public String getPasswordInfoText() {
        return passwordInfoText.getText();
    }
}