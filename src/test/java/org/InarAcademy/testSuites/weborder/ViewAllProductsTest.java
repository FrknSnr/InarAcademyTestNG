package org.InarAcademy.testSuites.weborder;

import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.LoginPage;
import org.InarAcademy.pages.weborder.ViewAllProductsPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;

public class ViewAllProductsTest extends TestBase {
    private ViewAllProductsPage viewAllProductsPage;

    @BeforeMethod
    public void beforeTests() {
        HomePage homePage = new LoginPage(driver).login("Inar", "Academy");
        viewAllProductsPage = homePage.goToViewAllProductsPage();
        Assert.assertEquals(viewAllProductsPage.getViewAllProductsText(), "View All Products");
    }

    @Test
    public void verifyAllProductsHavePrice() {
        Assert.assertEquals(viewAllProductsPage.getNumberOfNames(), viewAllProductsPage.getNumberOfPrices());
    }

    @Test
    public void verifyDiscounts() {
        HashMap<String, Integer> productDiscountsMap = new HashMap<>();
        productDiscountsMap.put("MyMoney", 10);
        productDiscountsMap.put("FamilyAlbum", 15);
        productDiscountsMap.put("ScreenSaver", 10);
        productDiscountsMap.put("TechGadget", 5);
        productDiscountsMap.put("HomeDecor", 20);
        productDiscountsMap.put("FashionApparel", 0);
        productDiscountsMap.put("Electronics", 10);
        productDiscountsMap.put("HealthWellness", 5);
        productDiscountsMap.put("SportsEquipment", 15);
        productDiscountsMap.put("Books", 10);
        productDiscountsMap.put("KitchenAppliances", 0);
        productDiscountsMap.put("ToysGames", 10);

        Assert.assertEquals(productDiscountsMap, viewAllProductsPage.getProductDiscountMap());

    }
}
