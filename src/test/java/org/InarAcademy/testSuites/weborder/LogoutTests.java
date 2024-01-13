package org.InarAcademy.testSuites.weborder;

import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.WeborderLoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends TestBase {

    WeborderLoginPage weborderLoginPage;
    HomePage homePage;

    @BeforeMethod
    public void beforeTests() {
        weborderLoginPage = new WeborderLoginPage(driver);
        homePage = weborderLoginPage.login("Inar", "Academy");
    }

    @Test
    public void logoutTest() {
        weborderLoginPage = homePage.logout();
        Assert.assertEquals(weborderLoginPage.getLoginScreenTitle(), "Login");
    }
}