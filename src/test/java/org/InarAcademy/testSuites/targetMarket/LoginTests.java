package org.InarAcademy.testSuites.targetMarket;

import org.InarAcademy.InarConfig;
import org.InarAcademy.pages.targetMarket.HomePage;
import org.InarAcademy.pages.targetMarket.TargetMarketLoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    TargetMarketLoginPage targetMarketLoginPage;
    HomePage homePage;

    @BeforeMethod(groups = {"smoke"})
    public void beforeTests() {
        targetMarketLoginPage = new TargetMarketLoginPage(driver);
    }

    @Test
    public void loginScreenDefaultView() {
        Assert.assertEquals(targetMarketLoginPage.getTitleText(), "Login");
        Assert.assertEquals(targetMarketLoginPage.getTitleDescriptionText(), "for buy Something !");
        Assert.assertTrue(targetMarketLoginPage.isUsernameInputDisplayed());
        Assert.assertTrue(targetMarketLoginPage.isPasswordInputDisplayed());
        Assert.assertTrue(targetMarketLoginPage.isLoginButtonDisplayed());
        Assert.assertEquals(targetMarketLoginPage.getWelcomeText(), "Welcome Target Market, please login");
        Assert.assertEquals(targetMarketLoginPage.getUsernameInfoTexts(), """
                Username:
                standard_user,
                locked_out_user,
                problem_user,
                performance_glitch_user""");
        Assert.assertEquals(targetMarketLoginPage.getPasswordInfoTexts(), "Password:\n" + "secret_password");
    }

    @Test(description = "login with standard user", groups = {"smoke"})
    public void loginWithStandardUser() {
        homePage = targetMarketLoginPage.login(InarConfig.getProperty("target-market.standard.username"), InarConfig.getProperty("target-market.password"));
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome to the Target Market, standard_user!");
    }

    @Test(description = "login with locked out user")
    public void loginWithLockedOutUser() {
        targetMarketLoginPage.login(InarConfig.getProperty("target-market.locked-out.username"), InarConfig.getProperty("target-market.password"));
        Assert.assertEquals(targetMarketLoginPage.getUserNameErrorMessage(), "Your account is locked.");
    }

    @Test(description = "login with problem user")
    public void loginWithProblemUser() {
        homePage = targetMarketLoginPage.login(InarConfig.getProperty("target-market.problem.username"), InarConfig.getProperty("target-market.password"));
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome to the Target Market, problem_user!");
    }

    @Test(description = "login with performance glitch user")
    public void loginWithPerformanceGlitchUser() {
        homePage = targetMarketLoginPage.login(InarConfig.getProperty("target-market.performance-glitch.username"), InarConfig.getProperty("target-market.password"));
        Assert.assertTrue(homePage.isLoadingTextDisplayed());
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome to the Target Market, performance_glitch_user!");
    }

    @Test(description = "login with invalid username")
    public void loginWithInvalidUsername() {
        targetMarketLoginPage.login("invalid_username", InarConfig.getProperty("target-market.password"));
        Assert.assertEquals(targetMarketLoginPage.getUserNameErrorMessage(), "Invalid username");
        Assert.assertEquals(targetMarketLoginPage.getPasswordErrorMessage(), "Invalid password");
    }

    @Test(description = "login with invalid password")
    public void loginWithInvalidPassword() {
        targetMarketLoginPage.login(InarConfig.getProperty("target-market.standard.username"), "invalid_password");
        Assert.assertEquals(targetMarketLoginPage.getUserNameErrorMessage(), "Invalid username");
        Assert.assertEquals(targetMarketLoginPage.getPasswordErrorMessage(), "Invalid password");
    }

    @Test(description = "login with empty username")
    public void loginWithEmptyUsername() {
        targetMarketLoginPage.login("", InarConfig.getProperty("target-market.password"));
        Assert.assertEquals(targetMarketLoginPage.getUserNameErrorMessage(), "Please enter your username");
    }

    @Test(description = "login with empty password")
    public void loginWithEmptyPassword() {
        targetMarketLoginPage.login(InarConfig.getProperty("target-market.standard.username"), "");
        Assert.assertEquals(targetMarketLoginPage.getPasswordErrorMessage(), "Please enter your password");
    }
}