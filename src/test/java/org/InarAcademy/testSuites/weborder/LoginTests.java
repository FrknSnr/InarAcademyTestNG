/*
package org.InarAcademy.testSuites.weborder;

import org.InarAcademy.dataProvider.UsernameData;
import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.WeborderLoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.InarAcademy.dataProvider.PasswordData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    WeborderLoginPage weborderLoginPage;

    @BeforeMethod(groups = {"smoke"})
    public void beforeTests() {
        weborderLoginPage = new WeborderLoginPage(driver);
    }

    @Test
    public void loginScreenDefaultView() {
        Assert.assertTrue(weborderLoginPage.isUsernameInputDisplayed());
        Assert.assertTrue(weborderLoginPage.isPasswordInputDisplayed());
        Assert.assertTrue(weborderLoginPage.isLoginButtonDisplayed());
        Assert.assertEquals(weborderLoginPage.getDescriptionText(), "In order to log in, use the following information:");
        Assert.assertEquals(weborderLoginPage.getUsernameInfoText(), "Username: Inar");
        Assert.assertEquals(weborderLoginPage.getPasswordInfoText(), "Password: Academy");
    }

    */
/*@Test(groups = {"smoke"})
    public void loginWithValidCredentials() {
        HomePage homePage = weborderLoginPage.login("Inar", "Academy");
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome, Inar!");
    }

    @Test(dataProvider = "usernameData", dataProviderClass = UsernameData.class)
    public void loginWithInvalidUsername(String username, String password, String alertMessage) {
        weborderLoginPage.login(username, password);
        Assert.assertEquals(weborderLoginPage.getUsernameAlertMessage(), alertMessage);
    }

    @Test(dataProvider = "passwordData", dataProviderClass = PasswordData.class)
    public void loginWithInvalidPassword(String username, String password, String alertMessage) {
        weborderLoginPage.login(username, password);
        Assert.assertEquals(weborderLoginPage.getPasswordAlertMessage(), alertMessage);
    }*//*

}*/
