package org.InarAcademy.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//h1[@id='welcome-heading']")
    private WebElement welcomeText;
    @FindBy(id = "order-tab")
    private WebElement orderTab;
    @FindBy(id = "view-orders-tab")
    private WebElement viewAllOrdersTab;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }

    public OrderPage goToOrderPage() {
        orderTab.click();
        return new OrderPage(driver);
    }

    public ViewAllOrdersPage goToViewAllOrdersPage() {
        try {
            viewAllOrdersTab.click();
        } catch (ElementClickInterceptedException e) {
            viewAllOrdersTab.click();
        }
        return new ViewAllOrdersPage(driver);
    }
}