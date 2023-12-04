package org.InarAcademy.testSuites.targetMarket;

import org.InarAcademy.InarConfig;
import org.InarAcademy.pages.targetMarket.HomePage;
import org.InarAcademy.pages.targetMarket.LoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortingTests extends TestBase {

    private final InarConfig conf = new InarConfig();
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeTests() {
        loginPage = new LoginPage(driver);
    }

    @Test(description = "check the products are really sorted from A to Z when the user selects 'Sort By: A-Z' option")
    public void checkProductsSortedAToZ() {
        homePage = loginPage.login(conf.getProperties("target-market.standard.username"), conf.getProperties("target-market.password"));
        Assert.assertTrue(homePage.isProductsSortedAToZ());
    }

    @Test(description = "check the products are really sorted from Z to A when the user selects 'Sort By: Z-A' option")
    public void checkProductsSortedZToA() {
        homePage = loginPage.login(conf.getProperties("target-market.standard.username"), conf.getProperties("target-market.password"));
        Assert.assertTrue(homePage.isProductsSortedZToA());
    }

    @Test(description = "check the products are really sorted from high to low when the user selects 'Sort By: Highest Price' option")
    public void checkProductsSortedHighToLow() {
        homePage = loginPage.login(conf.getProperties("target-market.standard.username"), conf.getProperties("target-market.password"));
        Assert.assertTrue(homePage.isProductsSortedHighToLow());
    }

    @Test(description = "check the products are really sorted from low to high when the user selects 'Sort By: Lowest Price' option")
    public void checkProductsSortedLowToHigh() {
        homePage = loginPage.login(conf.getProperties("target-market.standard.username"), conf.getProperties("target-market.password"));
        Assert.assertTrue(homePage.isProductsSortedLowToHigh());
    }
}