package org.InarAcademy.testSuites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.pages.OrderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyCalculationTest {
    private WebDriver driver;
    private OrderPage orderPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        /**
         * to go to the order page, first we should log in,
         * after getting homepage object then we can go to order tab.
         */
        orderPage = new LoginPage(driver).login().goToOrderPage();
        Assert.assertTrue(orderPage.isOrderPageDisplayed());
    }

    /**
     * User checks whether the calculation is true or not.
     * Here the user selects the "HomeDecor" product.
     * The user sets the quantity to 5 and the discount to %20.
     * Then checks the calculation.
     */
    @Test
    public void calculate() {
        orderPage.calculate();
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
