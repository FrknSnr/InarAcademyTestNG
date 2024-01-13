package org.InarAcademy.testSuites.softAssertion;

import org.InarAcademy.pages.targetMarket.TargetMarketLoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertion extends TestBase {

    private static final Logger log = (Logger) LogManager.getLogger(SoftAssertion.class);
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
        softAssert.assertEquals(targetMarketLoginPage.getTitleDescriptionText(), "buy Something !", "Deliberately failing this assertion");
        log.error("\nThis is an error message");

        Assert.assertTrue(targetMarketLoginPage.isPasswordInputDisplayed());
        Assert.assertTrue(targetMarketLoginPage.isLoginButtonDisplayed());
        Assert.assertEquals(targetMarketLoginPage.getWelcomeText(), "Welcome Target Market, please login");

        softAssert.assertAll();
    }
}