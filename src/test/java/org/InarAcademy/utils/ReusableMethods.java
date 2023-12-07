package org.InarAcademy.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

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

    public static Object[][] getCsvData(String csvFileNameWithRelative, String splitCharacter) {

        ArrayList<ArrayList<String>> datas = new ArrayList<>();
        ArrayList<String> data;
        String[] temp = null;
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFileNameWithRelative), StandardCharsets.UTF_8));
            br.readLine(); // dummy reading to header (columns name)

            String line;
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                data = new ArrayList<>();
                temp = line.split(splitCharacter); // use comma as separator
                Collections.addAll(data, temp);
                datas.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(datas);
        // [[Ayse, female, Argentina], [Hatice, female, Belarus], [Zeynep, female, Argentina]]
        // [[us1, pass1], [us2, pass2], [us3, pass3]]
        assert temp != null;
        Object[][] objArray = new Object[datas.size()][temp.length]; // too dynamic
        for (int i = 0; i < objArray.length; i++) {
            // ArrayList to Array (toArray())
            objArray[i] = datas.get(i).toArray(); // datas.toArray() -> ArrayList -> Array ****onemli
            // Array to ArrayList (Arrays.asList(array))
        }
        // System.out.println(Arrays.toString(objArray[0]));
        return objArray;
    }
}