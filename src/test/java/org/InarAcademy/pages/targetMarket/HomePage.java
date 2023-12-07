package org.InarAcademy.pages.targetMarket;

import org.InarAcademy.pages.BasePage;
import org.InarAcademy.utils.ReusableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class HomePage extends BasePage {

    private static final Logger logger = Logger.getLogger(HomePage.class.getName());

    @FindBy(css = "h5[class='display-5']")
    private WebElement welcomeText;
    @FindBy(xpath = "//div[contains(text(), 'Loading...')]")
    private WebElement loading;
    @FindBy(xpath = "//a[@role='tab']")
    private List<WebElement> tabs;
    @FindBy(xpath = "//h5[@class='card-title']")
    private List<WebElement> productTitles;
    @FindBy(id = "sortType")
    private WebElement sortTypeElement;
    @FindBy(xpath = "//strong[@class='text-danger']")
    private List<WebElement> productPrices;
    @FindBy(xpath = "//button[@class='btn btn-danger fs-3 w-100']")
    private List<WebElement> addToCartButtons;
    @FindBy(css = ".mx-3:nth-of-type(2)")
    private WebElement cartButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }

    public Boolean isLoadingTextDisplayed() {
        return loading.isDisplayed();
    }

    public Boolean isProductsSortedAToZ() {
        Select sortType = new Select(sortTypeElement);
        for (WebElement tab : tabs
        ) {
            List<String> titleList = getProductTitles(tab, sortType, "A-Z");
            List<String> sortedList = new ArrayList<>(titleList);
            Collections.sort(sortedList);
            if (!sortedList.equals(titleList)) {
                logMismatchDetails(tab.getText(), titleList, sortedList);
                return false;
            }
        }
        return true;
    }

    public Boolean isProductsSortedZToA() {
        Select sortType = new Select(sortTypeElement);
        for (WebElement tab : tabs
        ) {
            List<String> titleList = getProductTitles(tab, sortType, "Z-A");
            List<String> sortedList = new ArrayList<>(titleList);
            sortedList.sort(Collections.reverseOrder());
            if (!sortedList.equals(titleList)) {
                logMismatchDetails(tab.getText(), titleList, sortedList);
                return false;
            }
        }
        return true;
    }


    public List<String> getProductTitles(WebElement tab, Select sortType, String sortTypeText) {
        ReusableMethods.scrollAndClickToElement(driver, tab);
        sortType.selectByVisibleText(sortTypeText);

        List<String> titleList = new ArrayList<>();
        for (WebElement productTitle : productTitles) {
            titleList.add(productTitle.getText().toLowerCase());
        }
        return titleList;
    }

    public List<String> getProductPrices(WebElement tab, Select sortType, String sortTypeText) {
        ReusableMethods.scrollAndClickToElement(driver, tab);
        sortType.selectByVisibleText(sortTypeText);

        List<String> titleList = new ArrayList<>();
        for (WebElement productPrice : productPrices) {
            titleList.add(ReusableMethods.getSubstringOfText(productPrice.getText(), 1, productPrice.getText().length()));
        }
        return titleList;
    }

    public Boolean isProductsSortedHighToLow() {
        Select sortType = new Select(sortTypeElement);
        for (WebElement tab : tabs
        ) {
            List<String> priceList = getProductPrices(tab, sortType, "Highest Price");

            List<Integer> integerList = new ArrayList<>();
            for (String price : priceList
            ) {
                int priceInt = Integer.parseInt(price);
                integerList.add(priceInt);
            }

            integerList.sort(Collections.reverseOrder());

            List<String> sortedList = new ArrayList<>();
            for (int price : integerList
            ) {
                sortedList.add(String.valueOf(price));
            }
            if (!sortedList.equals(priceList)) {
                logMismatchDetails(tab.getText(), priceList, sortedList);
                return false;
            }
        }
        return true;
    }

    public Boolean isProductsSortedLowToHigh() {
        Select sortType = new Select(sortTypeElement);
        for (WebElement tab : tabs
        ) {
            List<String> priceList = getProductPrices(tab, sortType, "Lowest Price");

            List<Integer> integerList = new ArrayList<>();
            for (String price : priceList
            ) {
                int priceInt = Integer.parseInt(price);
                integerList.add(priceInt);
            }

            Collections.sort(integerList);

            List<String> sortedList = new ArrayList<>();
            for (int price : integerList
            ) {
                sortedList.add(String.valueOf(price));
            }
            if (!sortedList.equals(priceList)) {
                logMismatchDetails(tab.getText(), priceList, sortedList);
                return false;
            }
        }
        return true;
    }

    public void placeOrder(String tabName, String productName) {
        for (WebElement tabElement : tabs
        ) {
            if (tabElement.getText().equalsIgnoreCase(tabName)) {
                ReusableMethods.scrollAndClickToElement(driver, tabElement);
                break;
            }
        }
        for (int i = 0; i < productTitles.size(); i++) {
            if (productTitles.get(i).getText().equalsIgnoreCase(productName)) {
                ReusableMethods.scrollAndClickToElement(driver, addToCartButtons.get(i));
                break;
            }
        }
    }

    public ShoppingCartModalPage goToCart() {
        ReusableMethods.scrollAndClickToElement(driver, cartButton);
        return new ShoppingCartModalPage(driver);
    }

    private void logMismatchDetails(String tabText, List<String> titleList, List<String> sortedList) {
        logger.warning("Product titles mismatch in tab: " + tabText);
        logger.warning("Original List: " + titleList);
        logger.warning("Sorted List:   " + sortedList);
    }
}