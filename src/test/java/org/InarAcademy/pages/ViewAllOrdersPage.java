package org.InarAcademy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.List;

public class ViewAllOrdersPage extends BasePage {

    @FindBy(xpath = "//h1[text()='View All Orders']")
    private WebElement viewAllOrdersText;
    @FindBy(xpath = "//td/span")
    private List<WebElement> orderDetails;
    @FindBy(xpath = "//button[text()='Add More Data']")
    private WebElement addMoreDataButton;
    @FindBy(xpath = "//button[text()='Check All']")
    private WebElement checkAllButton;
    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement deleteButton;
    @FindBy(css = ".form-check-input")
    private List<WebElement> checkboxes;
    @FindBy(xpath = "//p[text()='No orders available.']")
    private WebElement noOrdersAvailableText;

    public ViewAllOrdersPage(WebDriver driver) {
        super(driver);
    }

    public String getViewAllOrdersText() {
        return viewAllOrdersText.getText();
    }

    public void checkOrder(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            Assert.assertTrue(orderDetails.get(i).getText().equalsIgnoreCase(list.get(i)));
        }
    }

    public void addAndDeleteData(int recurrence) {
        int i = 0;
        while (i < recurrence) {
            addMoreDataButton.click();
            i++;
        }
        Assert.assertEquals(checkboxes.size(), recurrence);
        checkAllButton.click();
        deleteButton.click();
    }

    public String getNoOrdersAvailableText() {
        return noOrdersAvailableText.getText();
    }
}
