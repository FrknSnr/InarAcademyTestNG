package org.InarAcademy.testSuites.order;

import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.pages.OrderPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidCardNumberTests extends TestBase {

    OrderPage orderPage;

    @BeforeMethod
    public void beforeTests() {
        HomePage homePage = new LoginPage(driver).login("Inar", "Academy");
        orderPage = homePage.goToOrderPage();
    }

    @Test(description = "Invalid card number not starting with '3' for American Express")
    public void invalidCardNumberForAmericanExpress() {
        String errorMessage = orderPage.fillPaymentInformations("American Express", "543412126565953", "01/28");
        Assert.assertEquals(errorMessage, "Card number is not valid");
    }

    @Test(description = "Invalid card number not starting with '4' for Visa")
    public void invalidCardNumberForVisa() {
        String errorMessage = orderPage.fillPaymentInformations("Visa", "343412126565952", "01/28");
        Assert.assertEquals(errorMessage, "Card number is not valid");
    }

    @Test(description = "Invalid card number not starting with '5' for MasterCard")
    public void invalidCardNumberForMasterCard() {
        String errorMessage = orderPage.fillPaymentInformations("MasterCard", "443412126565952", "01/28");
        Assert.assertEquals(errorMessage, "Card number is not valid");
    }
}