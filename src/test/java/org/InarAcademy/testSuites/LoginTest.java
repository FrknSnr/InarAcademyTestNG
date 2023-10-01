package org.InarAcademy.testSuites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.InarAcademy.pages.HomePage;
import org.InarAcademy.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginToWebOrder() {
        homePage = loginPage.login();
        Assert.assertEquals(homePage.getWelcomeText(), "Welcome, Inar!");
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
