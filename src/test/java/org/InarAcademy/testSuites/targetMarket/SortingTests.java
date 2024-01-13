package org.InarAcademy.testSuites.targetMarket;

import org.InarAcademy.InarConfig;
import org.InarAcademy.pages.targetMarket.HomePage;
import org.InarAcademy.pages.targetMarket.TargetMarketLoginPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortingTests extends TestBase {
    HomePage homePage;
    TargetMarketLoginPage targetMarketLoginPage;

    @BeforeMethod
    public void beforeTests() {
        targetMarketLoginPage = new TargetMarketLoginPage(driver);
    }

    @Test(description = "check the products are really sorted from A to Z when the user selects 'Sort By: A-Z' option")
    public void checkProductsSortedAToZ() {
        homePage = targetMarketLoginPage.login(InarConfig.getProperty("target-market.standard.username"), InarConfig.getProperty("target-market.password"));
        Assert.assertTrue(homePage.isProductsSortedAToZ());
    }

    @Test(description = "check the products are really sorted from Z to A when the user selects 'Sort By: Z-A' option")
    public void checkProductsSortedZToA() {
        homePage = targetMarketLoginPage.login(InarConfig.getProperty("target-market.standard.username"), InarConfig.getProperty("target-market.password"));
        Assert.assertTrue(homePage.isProductsSortedZToA());
    }

    @Test(description = "check the products are really sorted from high to low when the user selects 'Sort By: Highest Price' option")
    public void checkProductsSortedHighToLow() {
        homePage = targetMarketLoginPage.login(InarConfig.getProperty("target-market.standard.username"), InarConfig.getProperty("target-market.password"));
        Assert.assertTrue(homePage.isProductsSortedHighToLow());
    }

    @Test(description = "check the products are really sorted from low to high when the user selects 'Sort By: Lowest Price' option")
    public void checkProductsSortedLowToHigh() {
        homePage = targetMarketLoginPage.login(InarConfig.getProperty("target-market.standard.username"), InarConfig.getProperty("target-market.password"));
        Assert.assertTrue(homePage.isProductsSortedLowToHigh());
    }
}