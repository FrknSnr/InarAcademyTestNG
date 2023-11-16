package org.InarAcademy.pages.targetMarket;

import org.InarAcademy.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//h5[@class='display-5']")
    private WebElement welcomeText;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }
}
