package org.InarAcademy.testSuites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.pages.OrderPage;
import org.InarAcademy.pages.ViewAllOrdersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ViewOrderTest {

    private WebDriver driver;
    private OrderPage orderPage;
    private ViewAllOrdersPage viewAllOrdersPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        orderPage = new LoginPage(driver).login().goToOrderPage();
    }

    @Test
    public void placeAndCheckOrder() {
        viewAllOrdersPage = orderPage.placeOrder().goToViewAllOrdersPage();
        Assert.assertEquals(viewAllOrdersPage.getViewAllOrdersText(), "View All Orders");
        viewAllOrdersPage.checkOrder(orderPage.getOrderDetails());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
