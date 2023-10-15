package org.InarAcademy.testSuites;

import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.pages.OrderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyCalculationTest extends TestBase{

    @Test
    public void calculateOrder() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login("Inar","Academy");
        OrderPage orderPage = homePage.goToOrderPage();
        Assert.assertTrue(orderPage.isOrderPageDisplayed());

        orderPage.calculate();
    }
}
