package org.InarAcademy.testSuites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.InarAcademy.pages.ViewAllOrdersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckBoxTest {

    private WebDriver driver;
    private ViewAllOrdersPage viewAllOrdersPage;
    private HomePage homePage;

    private int recurrence = 5;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new LoginPage(driver).login();
        viewAllOrdersPage = homePage.goToViewAllOrdersPage();
        Assert.assertEquals(viewAllOrdersPage.getViewAllOrdersText(), "View All Orders");
    }

    @Test
    public void addAndDeleteData() {
        /**
         * we will click 'Add More Data' button 5 times.
         */
        viewAllOrdersPage.addAndDeleteData(recurrence);
        Assert.assertEquals(viewAllOrdersPage.getNoOrdersAvailableText(),"No orders available.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
