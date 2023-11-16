package org.InarAcademy.testSuites.weborder.order;

import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.LoginPage;
import org.InarAcademy.pages.weborder.OrderPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyCalculationTest extends TestBase {

    @Test
    public void calculateOrder() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login("Inar", "Academy");
        OrderPage orderPage = homePage.goToOrderPage();
        Assert.assertTrue(orderPage.isOrderPageDisplayed());

        orderPage.calculate();
        Assert.assertEquals(orderPage.getActualTotalPrice(), orderPage.getExpectedTotalPrice());
    }
}