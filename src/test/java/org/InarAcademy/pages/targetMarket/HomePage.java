package org.InarAcademy.pages.targetMarket;

import org.InarAcademy.pages.BasePage;
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
    @FindBy(xpath = "//h5[@class='display-5']")
    private WebElement welcomeText;
    @FindBy(xpath = "//div[contains(text(), 'Loading...')]\n")
    private WebElement loading;
    @FindBy(xpath = "//a[@role='tab']")
    private List<WebElement> tabs;
    @FindBy(xpath = "//h5[@class='card-title']")
    private List<WebElement> productTitles;
    @FindBy(id = "sortType")
    private WebElement sortTypeElement;


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

    public List<String> getProductTitles(WebElement tab, Select sortType, String sortTypeText) {
        tab.click();
        sortType.selectByVisibleText(sortTypeText);

        List<String> titleList = new ArrayList<>();
        for (WebElement productTitle : productTitles) {
            titleList.add(productTitle.getText().toLowerCase());
        }
        return titleList;
    }

    private void logMismatchDetails(String tabText, List<String> titleList, List<String> sortedList) {
        logger.warning("Product titles mismatch in tab: " + tabText);
        logger.warning("Original List: " + titleList);
        logger.warning("Sorted List:   " + sortedList);
    }
}