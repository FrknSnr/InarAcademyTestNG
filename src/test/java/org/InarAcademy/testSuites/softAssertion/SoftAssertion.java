package org.InarAcademy.testSuites.softAssertion;

import org.InarAcademy.pages.targetMarket.TargetMarketLoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertion extends TestBase {

    TargetMarketLoginPage targetMarketLoginPage;

    @BeforeMethod
    public void beforeTests() {
        targetMarketLoginPage = new TargetMarketLoginPage(driver);
    }

    @Test
    public void softAssertionTest() {
        SoftAssert softAssert = new SoftAssert();

        // Copy all assertions from checkingDefaultView method
        Assert.assertEquals(targetMarketLoginPage.getTitleText(), "Login");
        Assert.assertEquals(targetMarketLoginPage.getTitleDescriptionText(), "for buy Something !");
        Assert.assertTrue(targetMarketLoginPage.isUsernameInputDisplayed());
        softAssert.assertFalse(true, "Deliberately failing this assertion");
        Assert.assertTrue(targetMarketLoginPage.isPasswordInputDisplayed());
        Assert.assertTrue(targetMarketLoginPage.isLoginButtonDisplayed());
        Assert.assertEquals(targetMarketLoginPage.getWelcomeText(), "Welcome Target Market, please login");
        // Deliberately add a wrong control
        System.out.println("the others are executed as you see");
        softAssert.assertAll();
    }
}