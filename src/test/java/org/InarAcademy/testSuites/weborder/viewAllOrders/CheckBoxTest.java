package org.InarAcademy.testSuites.weborder.viewAllOrders;

import org.InarAcademy.pages.weborder.HomePage;
import org.InarAcademy.pages.weborder.LoginPage;
import org.InarAcademy.pages.weborder.ViewAllOrdersPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxTest extends TestBase {

    @Test
    public void addAndDeleteData() {
        HomePage homePage = new LoginPage(driver).login("Inar","Academy");
        ViewAllOrdersPage viewAllOrdersPage = homePage.goToViewAllOrdersPage();
        Assert.assertEquals(viewAllOrdersPage.getViewAllOrdersText(), "View All Orders");
        /*
          we will click 'Add More Data' button 5 times.
         */
        viewAllOrdersPage.addAndDeleteData(5);
        Assert.assertEquals(viewAllOrdersPage.getNoOrdersAvailableText(),"No orders available.");
    }
}
