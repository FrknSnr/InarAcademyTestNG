package org.InarAcademy.testSuites;

import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.pages.OrderPage;
import org.InarAcademy.pages.ViewAllOrdersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ViewOrderTest extends TestBase {

    @Test
    public void placeAndCheckOrder() {
        HomePage homePage = new LoginPage(driver).login("Inar", "Academy");
        OrderPage orderPage = homePage.goToOrderPage();
        /*
        first, we place order.
         */
        ViewAllOrdersPage viewAllOrdersPage = orderPage.placeOrder().goToViewAllOrdersPage();
        Assert.assertEquals(viewAllOrdersPage.getViewAllOrdersText(), "View All Orders");
        /*
        then, we can check the order.
         */
        viewAllOrdersPage.checkOrder(orderPage.getOrderDetails());
    }
}
