package org.InarAcademy.testSuites.weborder.viewAllOrders;

import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.LoginPage;
import org.InarAcademy.pages.weborder.OrderPage;
import org.InarAcademy.pages.weborder.ViewAllOrdersPage;
import org.InarAcademy.testSuites.TestBase;
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
