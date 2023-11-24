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
    HomePage homePage;

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
    public void loginWithStandardUser() {
        homePage = loginPage.login(conf.getProperties("target-market.standard.username"), conf.getProperties("target-market.password"));
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome to the Target Market, standard_user!");
    }

    @Test(description = "login with locked out user")
    public void loginWithLockedOutUser() {
        loginPage.login(conf.getProperties("target-market.locked-out.username"), conf.getProperties("target-market.password"));
        Assert.assertEquals(loginPage.getUserNameErrorMessage(), "Your account is locked.");
    }

    @Test(description = "login with problem user")
    public void loginWithProblemUser() {
        homePage = loginPage.login(conf.getProperties("target-market.problem.username"), conf.getProperties("target-market.password"));
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome to the Target Market, problem_user!");
    }

    @Test(description = "login with performance glitch user")
    public void loginWithPerformanceGlitchUser() {
        homePage = loginPage.login(conf.getProperties("target-market.performance-glitch.username"), conf.getProperties("target-market.password"));
        Assert.assertTrue(homePage.isLoadingTextDisplayed());
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome to the Target Market, performance_glitch_user!");
    }

    @Test(description = "login with invalid username")
    public void loginWithInvalidUsername() {
        loginPage.login("invalid_username", conf.getProperties("target-market.password"));
        Assert.assertEquals(loginPage.getUserNameErrorMessage(), "Invalid username");
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Invalid password");
    }

    @Test(description = "login with invalid password")
    public void loginWithInvalidPassword() {
        loginPage.login(conf.getProperties("target-market.standard.username"), "invalid_password");
        Assert.assertEquals(loginPage.getUserNameErrorMessage(), "Invalid username");
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Invalid password");
    }

    @Test(description = "login with empty username")
    public void loginWithEmptyUsername() {
        loginPage.login("", conf.getProperties("target-market.password"));
        Assert.assertEquals(loginPage.getUserNameErrorMessage(), "Please enter your username");
    }

    @Test(description = "login with empty password")
    public void loginWithEmptyPassword() {
        loginPage.login(conf.getProperties("target-market.standard.username"), "");
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Please enter your password");
    }
}