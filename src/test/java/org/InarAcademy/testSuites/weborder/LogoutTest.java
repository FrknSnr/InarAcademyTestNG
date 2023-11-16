package org.InarAcademy.testSuites.weborder;

import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.LoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void beforeTests() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.login("Inar", "Academy");
    }

    @Test
    public void logoutTest() {
        loginPage = homePage.logout();
        Assert.assertEquals(loginPage.getLoginScreenTitle(), "Login");
    }
}