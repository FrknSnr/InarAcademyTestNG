package org.InarAcademy.pages.targetMarket;

import org.InarAcademy.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    @FindBy(xpath = "(//strong)[1]")
    private WebElement title;
    @FindBy(xpath = "//i")
    private WebElement titleDescription;
    @FindBy(id = "login-username-input")
    private WebElement usernameInput;
    @FindBy(id = "login-password-input")
    private WebElement passwordInput;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(xpath = "//p[@class='text-dark mb-2 text-center']")
    private WebElement welcomeText;
    @FindBy(xpath = "//p[@class='text-dark mb-2 text-start d-flex flex-column me-5 ']")
    private WebElement usernameInfoTexts;
    @FindBy(xpath = "//p[@class='text-dark mb-2 text-start d-flex flex-column ms-5']")
    private WebElement passwordInfoTexts;
    @FindBy(id = "username-error-alert")
    private WebElement userNameErrorAlert;
    @FindBy(id = "password-error-alert")
    private WebElement passwordErrorMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get("https://inar-academy.netlify.app/target-market");
    }

    public HomePage login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public String getTitleText() {
        return title.getText();
    }

    public String getTitleDescriptionText() {
        return titleDescription.getText();
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

    public String getWelcomeText() {
        return welcomeText.getText();
    }

    public String getUsernameInfoTexts() {
        return usernameInfoTexts.getText();
    }

    public String getPasswordInfoTexts() {
        return passwordInfoTexts.getText();
    }

    public String getUserNameErrorMessage() {
        return userNameErrorAlert.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }
}
