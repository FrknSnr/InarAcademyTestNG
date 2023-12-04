package org.InarAcademy.pages.weborder;

import org.InarAcademy.pages.BasePage;
import org.InarAcademy.utils.ReusableMethods;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class OrderPage extends BasePage {
    private String PRODUCT;
    private String AMOUNT;
    private String PERCENTAGE;
    private String NAME;
    private String STREET;
    private String CITY;
    private String STATE;
    private String ZIP;
    private String CARD_TYPE;
    private String CARD_NO;
    private String EXPIRE_DATE;


    @FindBy(className = "order-page")
    private WebElement orderPage;
    @FindBy(id = "productSelect")
    private WebElement productDropdown;
    @FindBy(id = "quantityInput")
    private WebElement quantity;
    @FindBy(id = "unitPriceInput")
    private WebElement unitPrice;
    @FindBy(id = "discountInput")
    private WebElement discount;
    @FindBy(xpath = "//button[text() ='Calculate']")
    private WebElement calculateButton;
    @FindBy(id = "totalInput")
    private WebElement totalPrice;
    @FindBy(id = "name")
    private WebElement nameBox;
    @FindBy(id = "street")
    private WebElement streetBox;
    @FindBy(id = "city")
    private WebElement cityBox;
    @FindBy(id = "state")
    private WebElement stateBox;
    @FindBy(id = "zip")
    private WebElement zipBox;
    @FindBy(css = "input[name='cardType']")
    private List<WebElement> cardTypeElements;
    @FindBy(id = "cardNumber")
    private WebElement cardNumberBox;
    @FindBy(id = "expiryDate")
    private WebElement expiryDateBox;
    @FindBy(xpath = "//button[text() ='Process']")
    private WebElement processButton;
    @FindBy(css = ".alert-success")
    private WebElement orderAddedAlert;
    @FindBy(xpath = "//div/form/div[7]/span/em")
    private WebElement cardNumberIsNotValidMessage;

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isOrderPageDisplayed() {
        return orderPage.isDisplayed();
    }

    public void calculate(String product, String amount, String percentage) {
        PRODUCT = product;
        AMOUNT = amount;
        PERCENTAGE = percentage;

        ReusableMethods.selectByText(productDropdown, product);
        determineQuantity(amount);
        determineDiscount(percentage);
        calculateButton.click();
    }

    public HomePage placeOrder(String product, String amount, String percentage, String name, String street, String city, String state, String zip, String cardType, String cardNum, String expireDate) {
        PRODUCT = product;
        AMOUNT = amount;
        PERCENTAGE = percentage;
        NAME = name;
        STREET = street;
        CITY = city;
        STATE = state;
        ZIP = zip;
        CARD_TYPE = cardType;
        CARD_NO = cardNum;
        EXPIRE_DATE = expireDate;

        calculate(PRODUCT, AMOUNT, PERCENTAGE);
        ReusableMethods.scrollAndSendKeysToElement(driver, nameBox, NAME);
        ReusableMethods.scrollAndSendKeysToElement(driver, streetBox, STREET);
        ReusableMethods.scrollAndSendKeysToElement(driver, cityBox, CITY);
        ReusableMethods.scrollAndSendKeysToElement(driver, stateBox, STATE);
        ReusableMethods.scrollAndSendKeysToElement(driver, zipBox, ZIP);
        //Here we select the "visa" type. So among others, we choose the "visa".
        for (WebElement ele :
                cardTypeElements) {
            if (CARD_TYPE.equalsIgnoreCase(ele.getAttribute("id"))) {
                ReusableMethods.scrollAndClickToElement(driver, ele);
            }
        }
        ReusableMethods.scrollAndSendKeysToElement(driver, cardNumberBox, CARD_NO);
        ReusableMethods.scrollAndSendKeysToElement(driver, expiryDateBox, EXPIRE_DATE);
        try {
            processButton.click();
        } catch (ElementClickInterceptedException exception) {
            processButton.click();
        }
        Assert.assertEquals(orderAddedAlert.getText(), "New order has been successfully added.");
        return new HomePage(driver);
    }

    public List<String> getOrderDetails() {
        List<String> list = new ArrayList<>();
        list.add(NAME);
        list.add(PRODUCT);
        list.add(AMOUNT);
        list.add(ReusableMethods.getCurrentDate());
        list.add(STREET);
        list.add(CITY);
        list.add(STATE);
        list.add(ZIP);
        list.add(CARD_TYPE);
        list.add(CARD_NO);
        list.add(EXPIRE_DATE);
        return list;
    }

    private void determineQuantity(String desiredQuantity) {
        quantity.sendKeys(desiredQuantity);
    }

    private void determineDiscount(String percentage) {
        discount.sendKeys(percentage);
    }

    public int getActualTotalPrice() {
        return ReusableMethods.stringToInteger(totalPrice.getAttribute("value"));
    }

    public int getExpectedTotalPrice() {
        int unitPriceOfProduct = ReusableMethods.stringToInteger(unitPrice.getAttribute("value"));
        return (ReusableMethods.stringToInteger(AMOUNT) * unitPriceOfProduct) - (int) (ReusableMethods.stringToInteger(AMOUNT) * unitPriceOfProduct * ReusableMethods.stringToInteger(PERCENTAGE) / 100.0);
    }

    public String fillPaymentInformations(String cardType, String cardNum, String expireDate) {
        for (WebElement ele :
                cardTypeElements) {
            if (cardType.equalsIgnoreCase(ele.getAttribute("value"))) {
                ReusableMethods.scrollAndClickToElement(driver, ele);
            }
        }
        ReusableMethods.scrollAndSendKeysToElement(driver, cardNumberBox, cardNum);
        ReusableMethods.scrollAndSendKeysToElement(driver, expiryDateBox, expireDate);
        ReusableMethods.scrollAndClickToElement(driver, processButton);
        return cardNumberIsNotValidMessage.getText();
    }
}