package org.InarAcademy.testSuites.targetMarket;

import org.InarAcademy.InarConfig;
import org.InarAcademy.pages.targetMarket.CheckoutPage;
import org.InarAcademy.pages.targetMarket.HomePage;
import org.InarAcademy.pages.targetMarket.TargetMarketLoginPage;
import org.InarAcademy.pages.targetMarket.ShoppingCartModalPage;
import org.InarAcademy.testSuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTests extends TestBase {
    TargetMarketLoginPage targetMarketLoginPage;
    HomePage homePage;
    ShoppingCartModalPage shoppingCartModalPage;
    CheckoutPage checkoutPage;
    private static final String firstName = "Inar";
    private static final String lastName = "Academy";
    private static final String address = "TX, USA";
    private static final String cardNumber = "1234567891234567";
    private static final String phoneNumber = "1234567890";


    @BeforeMethod
    public void beforeTests() {
        targetMarketLoginPage = new TargetMarketLoginPage(driver);
    }

    @BeforeMethod(groups = "standardUser")
    public void loginAsStandardUser() {
        homePage = targetMarketLoginPage.login(InarConfig.getProperty("target-market.standard.username"), InarConfig.getProperty("target-market.password"));
    }


    @Test(groups = "standardUser",
            description = "First, we place an order for a laptop and complete the checkout process, then we check the order details.")
    public void placeOrderAndCheckout() {
        String productName = "MacBook Pro";
        homePage.placeOrder("Laptops", productName);
        shoppingCartModalPage = homePage.goToCart();

        shoppingCartModalPage.setTotalPriceOfAllItems();
        shoppingCartModalPage.setTotalNumberOfItems();

        Assert.assertEquals((shoppingCartModalPage.getPricePerItem(productName) * shoppingCartModalPage.getNumberOfItem(productName))
                , shoppingCartModalPage.getTotalPriceOfEachItem(productName));

        checkoutPage = shoppingCartModalPage.goToCheckout();
        Assert.assertEquals(checkoutPage.getCheckoutPageUrl(), "https://inar-academy.netlify.app/target-market/checkout");
        checkoutPage.fillCheckoutForm(firstName, lastName, address, cardNumber, phoneNumber);
        checkoutPage.submitCheckout();
        Assert.assertTrue(checkoutPage.isThanksMessageTitleDisplayed());
        Assert.assertEquals(checkoutPage.getHelloUserMessage(), "Hello, " + firstName + " " + lastName + "!");
        Assert.assertEquals(checkoutPage.getOrderReceivedMessage(), "Your order has been received.");
        Assert.assertEquals(checkoutPage.getTotalPriceMessage(), InarConfig.getProperty("target-market.total-price-of-all-items"));
        Assert.assertEquals(checkoutPage.getTotalProductCountMessage(), "Total Product Count: " + InarConfig.getProperty("target-market.total-number-of-items"));
    }


    @Test(groups = "standardUser",
            description = "First, we place an order for a smartphone, then we increase the amount of the product and complete the checkout process, " +
                    "then check the order details.")
    public void placeOrderThenIncreaseAmount() {
        String productName = "Huawei P30";
        homePage.placeOrder("Smartphones", productName);
        shoppingCartModalPage = homePage.goToCart();
        shoppingCartModalPage.increaseAmountBy(productName, 3);

        shoppingCartModalPage.setTotalPriceOfAllItems();
        shoppingCartModalPage.setTotalNumberOfItems();

        Assert.assertEquals((shoppingCartModalPage.getPricePerItem(productName) * shoppingCartModalPage.getNumberOfItem(productName))
                , shoppingCartModalPage.getTotalPriceOfEachItem(productName));

        checkoutPage = shoppingCartModalPage.goToCheckout();
        Assert.assertEquals(checkoutPage.getCheckoutPageUrl(), "https://inar-academy.netlify.app/target-market/checkout");
        checkoutPage.fillCheckoutForm(firstName, lastName, address, cardNumber, phoneNumber);
        checkoutPage.submitCheckout();
        Assert.assertTrue(checkoutPage.isThanksMessageTitleDisplayed());
        Assert.assertEquals(checkoutPage.getHelloUserMessage(), "Hello, " + firstName + " " + lastName + "!");
        Assert.assertEquals(checkoutPage.getOrderReceivedMessage(), "Your order has been received.");
        Assert.assertEquals(checkoutPage.getTotalPriceMessage(), InarConfig.getProperty("target-market.total-price-of-all-items"));
        Assert.assertEquals(checkoutPage.getTotalProductCountMessage(), "Total Product Count: " + InarConfig.getProperty("target-market.total-number-of-items"));
    }

    @Test(groups = "standardUser",
            description = "First, we place an order for a laptop and then we decrease the amount of the product and check the order details.")
    public void placeOrderThenDecreaseAmount() {
        String productName = "MacBook Pro";
        homePage.placeOrder("Laptops", productName);
        shoppingCartModalPage = homePage.goToCart();
        shoppingCartModalPage.decreaseAmountBy(productName, 1);
        Assert.assertEquals(shoppingCartModalPage.getEmptyCartMessage(), "Your cart is empty.");
    }

/*    @Test(groups = "standardUser",
            description = "We place order for more than one product and check the order details.")
    public void placeOrderForMultipleProducts() {
        String productName1 = "MacBook Pro";
        String productName2 = "iPhone X";
        homePage.placeOrder("Laptops", productName1);
        homePage.placeOrder("Smartphones", productName2);
        shoppingCartModalPage = homePage.goToCart();

        shoppingCartModalPage.setTotalPriceOfAllItems();
        shoppingCartModalPage.setTotalNumberOfItems();

        Assert.assertEquals((shoppingCartModalPage.getPricePerItem(productName1) * shoppingCartModalPage.getNumberOfItem(productName1))
                , shoppingCartModalPage.getTotalPriceOfEachItem(productName1));
        Assert.assertEquals((shoppingCartModalPage.getPricePerItem(productName2) * shoppingCartModalPage.getNumberOfItem(productName2))
                , shoppingCartModalPage.getTotalPriceOfEachItem(productName2));

        checkoutPage = shoppingCartModalPage.goToCheckout();
        Assert.assertEquals(checkoutPage.getCheckoutPageUrl(), "https://inar-academy.netlify.app/target-market/checkout");
        checkoutPage.fillCheckoutForm(firstName, lastName, address, cardNumber, phoneNumber);
        checkoutPage.submitCheckout();
        Assert.assertTrue(checkoutPage.isThanksMessageTitleDisplayed());
        Assert.assertEquals(checkoutPage.getHelloUserMessage(), "Hello, " + firstName + " " + lastName + "!");
        Assert.assertEquals(checkoutPage.getOrderReceivedMessage(), "Your order has been received.");
        Assert.assertEquals(checkoutPage.getTotalPriceMessage(), InarConfig.getProperty("target-market.total-price-of-all-items"));
        Assert.assertEquals(checkoutPage.getTotalProductCountMessage(), "Total Product Count: " + InarConfig.getProperty("target-market.total-number-of-items"));
    }*/
}