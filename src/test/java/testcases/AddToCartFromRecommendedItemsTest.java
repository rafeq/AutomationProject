package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import utils.ConfigReader;
import utils.ScreenRecording;
import utils.WebDriverManager;

import java.io.File;
import java.io.IOException;

public class AddToCartFromRecommendedItemsTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(AddToCartFromRecommendedItemsTest.class);
    private static ExtentReports extentReports;
    private ExtentTest extentTest;
    private WebDriver driver;
    private ScreenRecording screenRecorder;

    private final String websiteUrl = ConfigReader.getWebsiteUrl();
    private final String browserType = ConfigReader.getBrowserType();

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = WebDriverManager.getDriver();
        logger.info("Browser initialized: " + browserType);

        // Initialize ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/AddToCartFromRecommendedItemsTestReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Add To Cart from Recommended Items Test");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Tester", "Rafeek");
        extentReports.setSystemInfo("Browser", browserType);

        extentTest = extentReports.createTest("Add to Cart from Recommended Items Test");

        // Navigate to the website
        driver.get(websiteUrl);
        logger.info("Navigated to: " + websiteUrl);

        // Start screen recording
        screenRecorder = new ScreenRecording();
        screenRecorder.startRecording();
    }

    @Test
    public void testAddToCartFromRecommendedItems() {
        try {
            // Initialize HomePage
            HomePage homePage = new HomePage(driver);

            // Verify home page visibility
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible.");
            extentTest.pass("Home page is visible.");
            logger.info("Verified home page visibility.");

            // Scroll to Recommended Items section
            homePage.scrollToRecommendedItems();
            Assert.assertTrue(homePage.isRecommendedItemsVisible(), "'RECOMMENDED ITEMS' section is not visible.");
            extentTest.pass("'RECOMMENDED ITEMS' section is visible.");
            logger.info("Scrolled to and verified 'RECOMMENDED ITEMS' section.");

            // Add recommended product to cart
            homePage.clickAddToCartOnRecommendedItem();
            extentTest.info("Clicked 'Add To Cart' on a recommended product.");
            logger.info("Added recommended item to the cart.");

            // Navigate to the Cart page
            homePage.clickViewCart();
            extentTest.info("Clicked 'View Cart' button.");
            logger.info("Navigated to the cart page.");

            // Verify the product is added to the cart
            CartPage cartPage = new CartPage(driver);
            Assert.assertTrue(cartPage.isProductInCart(), "Product is not displayed in the cart.");
            extentTest.pass("Verified product is displayed in the cart.");
            logger.info("Product successfully added to cart.");

        } catch (Exception e) {
            handleTestFailure(e, "testAddToCartFromRecommendedItems");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            // Stop screen recording
            screenRecorder.stopRecording();

            // Handle test results
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = takeScreenshot(result.getName());
                String videoPath = screenRecorder.getVideoFile().getAbsolutePath();

                extentTest.fail("Test failed: " + result.getThrowable().getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                extentTest.fail("Test execution video:",
                        MediaEntityBuilder.createScreenCaptureFromPath(videoPath).build());

                logger.error("Test failed. Screenshot and video captured.");
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("Test passed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error during test tear down: ", e);
        } finally {
            // Flush ExtentReports and quit WebDriver
            extentReports.flush();
            WebDriverManager.quitDriver();
            logger.info("Test execution completed. Resources cleaned up.");
        }
    }

    private String takeScreenshot(String testName) {
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File(screenshotPath));
            logger.info("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            logger.error("Failed to save screenshot.", e);
        }
        return screenshotPath;
    }

    private void handleTestFailure(Exception e, String testName) {
        logger.error("Error during test execution: ", e);
        extentTest.fail("Test failed due to an exception: " + e.getMessage(),
                MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(testName)).build());
        Assert.fail("Test failed due to an exception: " + e.getMessage());
    }
}
