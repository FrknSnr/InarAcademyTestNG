package org.InarAcademy.testSuites.weborder;

import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.WeborderLoginPage;
import org.InarAcademy.pages.weborder.OrderPage;
import org.InarAcademy.pages.weborder.ViewAllOrdersPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ViewAllOrdersTests extends TestBase {

    HomePage homePage;

    @BeforeMethod
    public void beforeTests() {
        homePage = new WeborderLoginPage(driver).login("Inar", "Academy");
    }

    @Test
    public void addAndDeleteData() {
        ViewAllOrdersPage viewAllOrdersPage = homePage.goToViewAllOrdersPage();
        Assert.assertEquals(viewAllOrdersPage.getViewAllOrdersText(), "View All Orders");
        /*
          we will click 'Add More Data' button 5 times.
         */
        viewAllOrdersPage.addAndDeleteData(5);
        Assert.assertEquals(viewAllOrdersPage.getNoOrdersAvailableText(), "No orders available.");
    }

    @Test
    public void placeAndCheckOrder() {
        OrderPage orderPage = homePage.goToOrderPage();
        /*
        first, we place order.
         */
        ViewAllOrdersPage viewAllOrdersPage = orderPage.placeOrder(
                        "FamilyAlbum", "2", "15", "Inar", "Congress Ave.", "Austin",
                        "TX", "78701", "visa", "4938281746192845", "11/28")
                .goToViewAllOrdersPage();
        Assert.assertEquals(viewAllOrdersPage.getViewAllOrdersText(), "View All Orders");
        /*
        then, we can check the order.
         */
        viewAllOrdersPage.checkOrder(orderPage.getOrderDetails());
    }
}
