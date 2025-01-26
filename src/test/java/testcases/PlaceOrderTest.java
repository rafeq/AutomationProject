package testcases;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import pages.*;
import utils.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PlaceOrderTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(PlaceOrderTest.class);
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public void setUpExtentReports() {
        // Setting up ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/PlaceOrderTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Test
    public void testPlaceOrderRegisterBeforeCheckout() {
        test = extent.createTest("testPlaceOrderRegisterBeforeCheckout", "Verify placing an order after registering before checkout");
        Random random = new Random();
        int min = 50;
        int max = 100;
        int randomInRange = random.nextInt(max - min) + min;

        String websiteUrl = ConfigReader.getWebsiteUrl();

        try {
            // Step 1: Navigate to the URL
            logger.info("Navigating to the URL: " + websiteUrl);
            test.info("Navigating to the URL: " + websiteUrl);
            driver.get(websiteUrl);

            // Step 2: Verify that home page is visible successfully
            HomePage homePage = new HomePage(driver);
            logger.info("Verifying that home page is visible");
            test.info("Verifying that home page is visible");
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

            // Step 3: Click 'Signup / Login' button
            homePage.clickSignupLogin();

            // Step 4: Fill all details in Signup and create account
            SignupPage signupPage = new SignupPage(driver);
            Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
            signupPage.enterNameAndEmail("John Doe", randomInRange + "john.doe@example.com");
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
            checkoutPage.enterPaymentDetails("John Doe", "4111111111111111", "123", "12", "25");

            // Step 14: Click 'Pay and Confirm Order' button
            checkoutPage.clickPayAndConfirmOrder();

            // Step 15: Verify success message 'Your order has been placed successfully!'
            Assert.assertTrue(checkoutPage.isOrderSuccessMessageVisible(), "ORDER PLACED!");

            // Step 16: Click 'Delete Account' button
            accountPage.clickDeleteAccount();

            // Step 17: Verify 'ACCOUNT DELETED!' and click 'Continue' button
            Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'Account Deleted!' is not visible");
            accountPage.clickContinue();

            logger.info("Test Completed Successfully: testPlaceOrderRegisterBeforeCheckout");
            test.pass("Test Completed Successfully: testPlaceOrderRegisterBeforeCheckout");

        } catch (AssertionError e) {
            captureScreenshot("testPlaceOrderRegisterBeforeCheckout");
            logger.error("Test Failed: " + e.getMessage(), e);
            test.fail("Test Failed: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            captureScreenshot("testPlaceOrderRegisterBeforeCheckout");
            logger.error("Unexpected Error: " + e.getMessage(), e);
            test.fail("Unexpected Error: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDownExtentReports() {
        // Flushing the ExtentReports instance
        extent.flush();
    }

    private void captureScreenshot(String testName) {
        try {
            // Capture screenshot and save to specified location
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String filePath = "screenshots/" + testName + "_" + timestamp + ".png";
            FileUtils.copyFile(screenshot, new File(filePath));
            logger.info("Screenshot saved at: " + filePath);

            // Attach screenshot to the report
            test.addScreenCaptureFromPath(filePath, "Screenshot of failure");

        } catch (IOException e) {
            logger.error("Error while capturing screenshot: " + e.getMessage(), e);
            test.warning("Failed to capture screenshot");
        }
    }
}
