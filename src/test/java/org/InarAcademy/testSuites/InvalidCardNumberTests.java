package org.InarAcademy.testSuites;

import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.pages.OrderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidCardNumberTests extends TestBase {

    @Test(description = "Invalid card number not starting with '3' for American Express")
    public void invalidCardNumberForAmericanExpress() {
        HomePage homePage = new LoginPage(driver).login("Inar", "Academy");
        OrderPage orderPage = homePage.goToOrderPage();
        String errorMessage = orderPage.fillPaymentInformations("American Express", "543412126565953", "01/28");
        Assert.assertEquals(errorMessage, "Card number is not valid");
    }

    @Test(description = "Invalid card number not starting with '4' for Visa")
    public void invalidCardNumberForVisa() {
        HomePage homePage = new LoginPage(driver).login("Inar", "Academy");
        OrderPage orderPage = homePage.goToOrderPage();
        String errorMessage = orderPage.fillPaymentInformations("Visa", "343412126565952", "01/28");
        Assert.assertEquals(errorMessage, "Card number is not valid");
    }

    @Test(description = "Invalid card number not starting with '5' for MasterCard")
    public void invalidCardNumberForMasterCard() {
        HomePage homePage = new LoginPage(driver).login("Inar", "Academy");
        OrderPage orderPage = homePage.goToOrderPage();
        String errorMessage = orderPage.fillPaymentInformations("MasterCard", "443412126565952", "01/28");
        Assert.assertEquals(errorMessage, "Card number is not valid");
    }
}