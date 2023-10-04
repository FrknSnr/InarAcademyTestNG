package org.InarAcademy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    @FindBy(id = "login-username-input")
    private WebElement usernameInput;
    @FindBy(id = "login-password-input")
    private WebElement passwordInput;
    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public HomePage login() {
        driver.get("https://inar-academy.netlify.app/weborder");
        usernameInput.sendKeys("Inar");
        passwordInput.sendKeys("Academy");
        loginButton.click();
        return new HomePage(driver);
    }
}
