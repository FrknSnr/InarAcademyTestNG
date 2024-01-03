package org.InarAcademy.testSuites.test;

import org.InarAcademy.pages.weborder.LoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DenemeTest extends TestBase {

    LoginPage loginPage;

    @BeforeMethod
    public void beforeTests() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginScreenDefaultView() {
        Assert.assertTrue(loginPage.isUsernameInputDisplayed());
        Assert.assertTrue(loginPage.isPasswordInputDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        Assert.assertEquals(loginPage.getDescriptionText(), "In order to log in, use the following information:");
        Assert.assertEquals(loginPage.getUsernameInfoText(), "Username: Inar");
        Assert.assertEquals(loginPage.getPasswordInfoText(), "Password: Academy");
    }
}
