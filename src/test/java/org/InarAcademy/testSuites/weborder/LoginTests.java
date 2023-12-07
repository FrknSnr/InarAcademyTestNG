package org.InarAcademy.testSuites.weborder;

import org.InarAcademy.dataProvider.UsernameData;
import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.LoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.InarAcademy.dataProvider.PasswordData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

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

    @Test
    public void loginWithValidCredentials() {
        HomePage homePage = loginPage.login("Inar", "Academy");
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome, Inar!");
    }

    @Test(dataProvider = "usernameData", dataProviderClass = UsernameData.class)
    public void loginWithInvalidUsername(String username, String password, String alertMessage) {
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getUsernameAlertMessage(), alertMessage);
    }

    @Test(dataProvider = "passwordData", dataProviderClass = PasswordData.class)
    public void loginWithInvalidPassword(String username, String password, String alertMessage) {
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getPasswordAlertMessage(), alertMessage);
    }
}