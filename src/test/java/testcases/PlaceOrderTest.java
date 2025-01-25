package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;

import java.util.Random;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void testPlaceOrderRegisterBeforeCheckout() {
        String websiteUrl = ConfigReader.getWebsiteUrl();
        String browserType = ConfigReader.getBrowserType();
        Random random = new Random();
        // Generate a random number between 50 (inclusive) and 100 (exclusive)
        int min = 50;
        int max = 100;
        int randomInRange = random.nextInt(max - min) + min;

        // Step 1: Navigate to the URL
        driver.get(websiteUrl);

        // Step 2: Verify that home page is visible successfully
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // Step 3: Click 'Signup / Login' button
        homePage.clickSignupLogin();
        // Step 4: Fill all details in Signup and create account
        SignupPage signupPage = new SignupPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
        signupPage.enterNameAndEmail("John Doe", randomInRange+"john.doe@example.com");
        signupPage.clickSignupButton();

        AccountPage accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.isAccountInfoVisible(), "'Enter Account Information' is not visible");
        accountPage.enterAccountInfo("password123", "John", "Doe", "123 Test Street", "United States", "California", "Los Angeles", "90001", "1234567890");
        accountPage.clickCreateAccount();

        // Step 5: Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertTrue(accountPage.isAccountCreatedVisible(), "'Account Created!' is not visible");
        accountPage.clickContinue();

        // Step 6: Verify 'Logged in as username' at top
        Assert.assertTrue(homePage.isLoggedInAsUsernameVisible("John Doe"), "'Logged in as username' is not visible");

        // Step 7: Add products to cart
        homePage.addFirstProductToCart();

        // Step 8: Click 'Cart' button
        homePage.clickCart();

        // Step 9: Verify that cart page is displayed
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageVisible(), "Shopping Cart");

        // Step 10: Click 'Proceed To Checkout'
        cartPage.clickProceedToCheckout();

        // Step 11: Verify Address Details and Review Your Order
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address details are not visible");
        Assert.assertTrue(checkoutPage.isOrderReviewVisible(), "Order review is not visible");

        // Step 12: Enter description in comment text area and click 'Place Order'
        checkoutPage.enterComment("Please deliver between 9 AM and 12 PM.");
        checkoutPage.clickPlaceOrder();

        // Step 13: Enter payment details
        checkoutPage.enterPaymentDetails("John Doe", "4111111111111111", "123", "12" , "25");

        // Step 14: Click 'Pay and Confirm Order' button
        checkoutPage.clickPayAndConfirmOrder();

        // Step 15: Verify success message 'Your order has been placed successfully!'
        Assert.assertTrue(checkoutPage.isOrderSuccessMessageVisible(), "ORDER PLACED!");

        // Step 16: Click 'Delete Account' button
        accountPage.clickDeleteAccount();

        // Step 17: Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'Account Deleted!' is not visible");
        accountPage.clickContinue();
    }
}
