package org.InarAcademy.testSuites.targetMarket;

import org.InarAcademy.InarConfig;
import org.InarAcademy.pages.targetMarket.HomePage;
import org.InarAcademy.pages.targetMarket.LoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    private final static InarConfig conf = new InarConfig();
    LoginPage loginPage;

    @BeforeMethod
    public void beforeTests() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginScreenDefaultView() {
        Assert.assertEquals(loginPage.getTitleText(), "Login");
        Assert.assertEquals(loginPage.getTitleDescriptionText(), "for buy Something !");
        Assert.assertTrue(loginPage.isUsernameInputDisplayed());
        Assert.assertTrue(loginPage.isPasswordInputDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        Assert.assertEquals(loginPage.getWelcomeText(), "Welcome Target Market, please login");
        Assert.assertEquals(loginPage.getUsernameInfoTexts(), """
                Username:
                standard_user,
                locked_out_user,
                problem_user,
                performance_glitch_user""");
        Assert.assertEquals(loginPage.getPasswordInfoTexts(), "Password:\n" + "secret_password");
    }

    @Test(description = "login with standard user")
    public void loginWithValidCredentials() {
        HomePage homePage = loginPage.login(conf.getProperties("target-market.standard.username"), conf.getProperties("target-market.password"));
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome to the Target Market, standard_user!");
    }
}