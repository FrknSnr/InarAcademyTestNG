package org.InarAcademy.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderPage {

    private WebDriver driver;
    private final String product = "HomeDecor";
    private final String amount = "5";
    private final String percentage = "20";
    private final String name = "Inar";
    private final String street = "Congress Ave.";
    private final String city = "Austin";
    private final String state = "TX";
    private final String zip = "78701";
    private final String cardType = "visa";
    private final String cardNo = "4938281746192845";
    private final String expireDate = "11/28";
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
    @FindBy(xpath = "//div[@class='form-check']/input[@id='visa']")
    private WebElement cardTypeElement;
    @FindBy(id = "cardNumber")
    private WebElement cardNumberBox;
    @FindBy(id = "expiryDate")
    private WebElement expiryDateBox;
    @FindBy(xpath = "//button[text() ='Process']")
    private WebElement processButton;
    @FindBy(css = ".alert-success")
    private WebElement orderAddedAlert;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public Boolean isOrderPageDisplayed() {
        return orderPage.isDisplayed();
    }

    public void calculate() {
        selectByText(productDropdown, product);
        determineQuantity(amount);
        determineDiscount(percentage);
        calculateButton.click();
        checkTotalPrice();
    }

    public HomePage placeOrder() {
        calculate();
        scrollAndSendKeysToElement(nameBox, name);
        scrollAndSendKeysToElement(streetBox, street);
        scrollAndSendKeysToElement(cityBox, city);
        scrollAndSendKeysToElement(stateBox, state);
        scrollAndSendKeysToElement(zipBox, zip);
        scrollAndClickToElement(cardTypeElement);
        scrollAndSendKeysToElement(cardNumberBox, cardNo);
        scrollAndSendKeysToElement(expiryDateBox, expireDate);
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
        list.add(name);
        list.add(product);
        list.add(amount);
        list.add(getCurrentDate());
        list.add(street);
        list.add(city);
        list.add(state);
        list.add(zip);
        list.add(cardType);
        list.add(cardNo);
        list.add(expireDate);
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

    private void checkTotalPrice() {
        int unitPriceOfProduct = stringToInteger(unitPrice.getAttribute("value"));
        int expectedTotal = (stringToInteger(amount) * unitPriceOfProduct) - (int) (stringToInteger(amount) * unitPriceOfProduct * stringToInteger(percentage) / 100.0);
        Assert.assertEquals(stringToInteger(totalPrice.getAttribute("value")), expectedTotal);
    }

    private int stringToInteger(String value) {
        return Integer.parseInt(value);
    }
}
