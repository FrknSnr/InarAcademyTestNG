package org.InarAcademy.pages.targetMarket;

import org.InarAcademy.pages.BasePage;
import org.InarAcademy.utils.ReusableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {


    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "address")
    private WebElement addressInput;
    @FindBy(id = "cardNumber")
    private WebElement cardNumberInput;
    @FindBy(id = "phoneNumber")
    private WebElement phoneNumberInput;
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;
    @FindBy(css = ".display-1:nth-child(3)")
    private WebElement thanksMessageTitle;
    @FindBy(css = ".display-4")
    private WebElement helloUserMessage;
    @FindBy(css = ".fs-3:nth-child(5)")
    private WebElement orderReceivedMessage;
    @FindBy(css = ".fs-3:nth-child(6)")
    private WebElement totalPriceMessage;
    @FindBy(css = ".fs-3:nth-child(7)")
    private WebElement totalProductMessage;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutPageUrl() {
        return driver.getCurrentUrl();
    }

    public void fillCheckoutForm(String firstName, String lastName, String address, String cardNumber, String phoneNumber) {
        ReusableMethods.scrollAndSendKeysToElement(driver, firstNameInput, firstName);
        ReusableMethods.scrollAndSendKeysToElement(driver, lastNameInput, lastName);
        ReusableMethods.scrollAndSendKeysToElement(driver, addressInput, address);
        ReusableMethods.scrollAndSendKeysToElement(driver, cardNumberInput, cardNumber);
        ReusableMethods.scrollAndSendKeysToElement(driver, phoneNumberInput, phoneNumber);
    }

    public void submitCheckout() {
        submitButton.click();
    }

    public Boolean isThanksMessageTitleDisplayed() {
        return thanksMessageTitle.isDisplayed();
    }

    public String getHelloUserMessage() {
        return helloUserMessage.getText();
    }

    public String getOrderReceivedMessage() {
        return orderReceivedMessage.getText();
    }

    public String getTotalPriceMessage() {
        return totalPriceMessage.getText();
    }

    public String getTotalProductCountMessage() {
        return (totalProductMessage.getText());
    }
}
