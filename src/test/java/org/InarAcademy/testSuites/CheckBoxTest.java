package org.InarAcademy.testSuites;

import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.pages.ViewAllOrdersPage;
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
