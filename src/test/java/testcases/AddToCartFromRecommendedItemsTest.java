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
import utils.WebDriverManager;

import java.io.File;
import java.io.IOException;

public class AddToCartFromRecommendedItemsTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(AddToCartFromRecommendedItemsTest.class);
    private static ExtentReports extentReports;
    private ExtentTest extentTest;
    String websiteUrl = ConfigReader.getWebsiteUrl();
    String browserType = ConfigReader.getBrowserType();
    private WebDriver driver;  // Ensure WebDriver is initialized correctly

    // Initialize ExtentReports
    @BeforeMethod
    public void setUp() {
        // Setting up WebDriver and ExtentReports
        driver = WebDriverManager.getDriver();  // Ensure driver is initialized here
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/AddToCartFromRecommendedItemsTestReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Add To Cart from Recommended Items Test");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Tester", "Your Name");
        extentReports.setSystemInfo("Browser", browserType);

        extentTest = extentReports.createTest("Add to Cart from Recommended Items Test");

        driver.get(websiteUrl);  // Navigate to URL in the setup
        logger.info("Navigated to the website: " + websiteUrl);
    }

    @Test
    public void testAddToCartFromRecommendedItems() {
        try {
            // Step 2: Verify that home page is visible successfully
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
            extentTest.pass("Home page is visible.");
            logger.info("Home page is visible.");

            // Step 3: Scroll to bottom of page
            homePage.scrollToRecommendedItems();
            extentTest.info("Scrolled to recommended items section.");
            logger.info("Scrolled to recommended items section.");

            // Step 4: Verify 'RECOMMENDED ITEMS' are visible
            Assert.assertTrue(homePage.isRecommendedItemsVisible(), "'RECOMMENDED ITEMS' are not visible");
            extentTest.pass("'RECOMMENDED ITEMS' section is visible.");
            logger.info("'RECOMMENDED ITEMS' section is visible.");

            // Step 5: Click on 'Add To Cart' on Recommended product
            homePage.clickAddToCartOnRecommendedItem();
            extentTest.info("Clicked 'Add To Cart' on a recommended product.");
            logger.info("Clicked 'Add To Cart' on a recommended product.");

            // Step 6: Click on 'View Cart' button
            homePage.clickViewCart();
            extentTest.info("Clicked 'View Cart'.");
            logger.info("Clicked 'View Cart'.");

            // Step 7: Verify that product is displayed in cart page
            CartPage cartPage = new CartPage(driver);
            Assert.assertTrue(cartPage.isProductInCart(), "Product is not displayed in the cart page");
            extentTest.pass("Product is successfully added to the cart.");
            logger.info("Product is successfully added to the cart.");

        } catch (Exception e) {
            logger.error("Error encountered during test execution", e);
            extentTest.fail("Test failed due to an exception",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("testAddToCartFromRecommendedItems_Failure")).build());
            Assert.fail("Test failed due to an exception.");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (ITestResult.FAILURE == result.getStatus()) {
                String screenshotPath = takeScreenshot(result.getName() + "_Failure");
                extentTest.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }
            logger.info("Test execution complete.");
            extentReports.flush();  // Ensure the report is written to disk
            WebDriverManager.quitDriver();  // Close the WebDriver after the test
        } else {
            logger.error("WebDriver is null, unable to take screenshot or close the driver.");
        }
    }

    private String takeScreenshot(String testName) {
        if (driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
            try {
                FileHandler.copy(screenshot, new File(screenshotPath));
                logger.info("Screenshot taken for test: " + testName);
            } catch (IOException e) {
                logger.error("Failed to take screenshot", e);
            }
            return screenshotPath;
        } else {
            logger.error("Driver is null, unable to take screenshot.");
            return "";
        }
    }
}
