package org.InarAcademy.testSuites;

import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
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
        homePage.logout();
        Assert.assertEquals(loginPage.getLoginScreenTitle(), "Login");
    }
}