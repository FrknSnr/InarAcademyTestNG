package org.InarAcademy.pages.weborder;

import org.InarAcademy.pages.BasePage;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @FindBy(xpath = "//input[@name='cardType']")
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
        PRODUCT= product;
        AMOUNT= amount;
        PERCENTAGE= percentage;

        selectByText(productDropdown, product);
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
        scrollAndSendKeysToElement(nameBox, NAME);
        scrollAndSendKeysToElement(streetBox, STREET);
        scrollAndSendKeysToElement(cityBox, CITY);
        scrollAndSendKeysToElement(stateBox, STATE);
        scrollAndSendKeysToElement(zipBox, ZIP);
        //Here we select the "visa" type. So among others, we choose the "visa".
        for (WebElement ele :
                cardTypeElements) {
            if (CARD_TYPE.equalsIgnoreCase(ele.getAttribute("id"))) {
                scrollAndClickToElement(ele);
            }
        }
        scrollAndSendKeysToElement(cardNumberBox, CARD_NO);
        scrollAndSendKeysToElement(expiryDateBox, EXPIRE_DATE);
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
        list.add(getCurrentDate());
        list.add(STREET);
        list.add(CITY);
        list.add(STATE);
        list.add(ZIP);
        list.add(CARD_TYPE);
        list.add(CARD_NO);
        list.add(EXPIRE_DATE);
        return list;
    }

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return currentDate.format(formatter);
    }

    private void scrollAndClickToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (ElementClickInterceptedException exception) {
            element.click();
        }
    }

    private void scrollAndSendKeysToElement(WebElement element, String keys) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    private void selectByText(WebElement element, String visibleText) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
    }

    private void determineQuantity(String desiredQuantity) {
        quantity.sendKeys(desiredQuantity);
    }

    private void determineDiscount(String percentage) {
        discount.sendKeys(percentage);
    }

    public int getActualTotalPrice() {
        return stringToInteger(totalPrice.getAttribute("value"));
    }

    public int getExpectedTotalPrice() {
        int unitPriceOfProduct = stringToInteger(unitPrice.getAttribute("value"));
        return (stringToInteger(AMOUNT) * unitPriceOfProduct) - (int) (stringToInteger(AMOUNT) * unitPriceOfProduct * stringToInteger(PERCENTAGE) / 100.0);
    }

    private int stringToInteger(String value) {
        return Integer.parseInt(value);
    }

    public String fillPaymentInformations(String cardType, String cardNum, String expireDate) {
        for (WebElement ele :
                cardTypeElements) {
            if (cardType.equalsIgnoreCase(ele.getAttribute("value"))) {
                scrollAndClickToElement(ele);
            }
        }
        scrollAndSendKeysToElement(cardNumberBox, cardNum);
        scrollAndSendKeysToElement(expiryDateBox, expireDate);
        scrollAndClickToElement(processButton);
        return cardNumberIsNotValidMessage.getText();
    }
}