package org.InarAcademy.testSuites;

import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @Test
    public void loginToWebOrder() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login("Inar", "Academy");
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome, Inar!");
    }
}