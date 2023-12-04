package org.InarAcademy.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReusableMethods {
    static WebDriverWait wait;

    public static String getSubstringOfText(String text, int beginIndex, int endIndex) {
        return text.substring(beginIndex, endIndex);
    }

    public static void scrollAndClickToElement(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            element.click();
        } catch (Exception exception) {
            element.click();
        }
    }

    public static void scrollAndSendKeysToElement(WebDriver driver, WebElement element, String keys) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public static void selectByText(WebElement element, String visibleText) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
    }

    public static int stringToInteger(String value) {
        return Integer.parseInt(value);
    }

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return currentDate.format(formatter);
    }

    public static void scrollToTopOfThePage(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToLocation(0,0).build().perform();
    }
}