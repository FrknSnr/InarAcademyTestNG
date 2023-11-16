package org.InarAcademy.testSuites;

import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.utils.TestDatas;
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

    @Test(dataProvider = "usernameData", dataProviderClass = TestDatas.class)
    public void loginWithInvalidUsername(String username, String password, String alertMessage) {
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getUsernameAlertMessage(), alertMessage);
    }

    @Test(dataProvider = "passwordData", dataProviderClass = TestDatas.class)
    public void loginWithInvalidPassword(String username, String password, String alertMessage) {
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getPasswordAlertMessage(), alertMessage);
    }
}