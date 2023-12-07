package org.InarAcademy.pages.targetMarket;

import org.InarAcademy.InarConfig;
import org.InarAcademy.pages.BasePage;
import org.InarAcademy.utils.ReusableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartModalPage extends BasePage {

    @FindBy(xpath = "//h3[@class='mb-0 text-dark fs-3 fw-semibold']")
    private List<WebElement> itemNames;
    @FindBy(xpath = "//p[@class='text-muted mb-0 fs-4']")
    private List<WebElement> itemCosts;
    @FindBy(xpath = "//p[@class='mb-0 fs-2 fw-bold']")
    private WebElement totalPriceElement;
    @FindBy(xpath = "//span[@class='fs-4']")
    private List<WebElement> numberOfItem;
    @FindBy(xpath = "//button[@class='fs-4 ms-2 btn btn-outline-dark btn-sm']")
    private List<WebElement> increaseAmountButtons;
    @FindBy(xpath = "//button[@class='fs-4 me-2 btn btn-outline-dark btn-sm']")
    private List<WebElement> decreaseAmountButtons;
    @FindBy(css = "p[class='fs-4']")
    private WebElement cartIsEmpty;
    @FindBy(css = ".mt-2")
    private WebElement goToCheckoutButton;
    @FindBy(css = ".ms-auto")
    private WebElement closeCartButton;


    public ShoppingCartModalPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getPricePerItem(String itemName) {
        String pricePerItem = "";
        for (int i = 0; i < itemNames.size(); i++) {
            if (itemNames.get(i).getText().equals(itemName)) {
                pricePerItem = itemCosts.get(i * 2).getText();
            }
        }
        return Integer.parseInt(ReusableMethods.getSubstringOfText(pricePerItem, 1, pricePerItem.length() - 11));
    }

    public int getTotalPriceOfEachItem(String itemName) {
        String pricePerItem = "";
        for (int i = 0; i < itemNames.size(); i++) {
            if (itemNames.get(i).getText().equals(itemName)) {
                pricePerItem = itemCosts.get((i * 2) + 1).getText();
            }
        }
        return Integer.parseInt(ReusableMethods.getSubstringOfText(pricePerItem, 8, pricePerItem.length()));
    }

    public int getNumberOfItem(String itemName) {
        int numberOfItem = 0;
        for (int i = 0; i < itemNames.size(); i++) {
            if (itemNames.get(i).getText().equals(itemName)) {
                numberOfItem = Integer.parseInt(this.numberOfItem.get(i).getText());
            }
        }
        return numberOfItem;
    }

    public void setTotalNumberOfItems() {
        int totalNumberOfItems = 0;
        for (WebElement element : numberOfItem) {
            totalNumberOfItems += Integer.parseInt(element.getText());
        }
        InarConfig.setProperty("target-market.total-number-of-items", String.valueOf(totalNumberOfItems));
    }

    public void setTotalPriceOfAllItems() {
        String totalPrice = totalPriceElement.getText();
        int startIndex = totalPrice.indexOf('$') + 1;
        int endIndex = totalPrice.length();
        totalPrice = ReusableMethods.getSubstringOfText(totalPrice, startIndex, endIndex);
        double totalPriceOfAllItems = Double.parseDouble(totalPrice);
        InarConfig.setProperty("target-market.total-price-of-all-items", String.format("Total Price: $%.2f", totalPriceOfAllItems));
    }

    public void increaseAmountBy(String itemName, int amount) {
        for (int i = 0; i < itemNames.size(); i++) {
            if (itemNames.get(i).getText().equals(itemName)) {
                for (int j = 0; j < amount; j++) {
                    ReusableMethods.scrollAndClickToElement(driver, increaseAmountButtons.get(i));
                }
            }
        }
    }

    public void decreaseAmountBy(String itemName, int amount) {
        for (int i = 0; i < itemNames.size(); i++) {
            if (itemNames.get(i).getText().equals(itemName)) {
                for (int j = 0; j < amount; j++) {
                    ReusableMethods.scrollAndClickToElement(driver, decreaseAmountButtons.get(i));
                }
            }
        }
    }

    public String getEmptyCartMessage() {
        return cartIsEmpty.getText();
    }

    public HomePage closeCart() {
        ReusableMethods.scrollAndClickToElement(driver, closeCartButton);
        return new HomePage(driver);
    }

    public CheckoutPage goToCheckout() {
        ReusableMethods.scrollAndClickToElement(driver, goToCheckoutButton);
        return new CheckoutPage(driver);
    }
}