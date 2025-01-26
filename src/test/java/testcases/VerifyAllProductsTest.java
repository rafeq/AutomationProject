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
import pages.HomePage;
import pages.ProductsPage;
import pages.ProductDetailPage;
import utils.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class VerifyAllProductsTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(VerifyAllProductsTest.class);
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public void setUpExtentReports() {
        // Setting up ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/VerifyAllProductsTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Test
    public void testAllProductsAndDetailPage() {
        // Initializing the ExtentTest instance for reporting
        test = extent.createTest("testAllProductsAndDetailPage", "Verify all products page and product details");

        // Fetching the website URL from the config file
        String websiteUrl = ConfigReader.getWebsiteUrl();

        logger.info("Test Started: testAllProductsAndDetailPage");
        test.info("Test Started: testAllProductsAndDetailPage");

        try {
            // Step 1: Navigate to the website URL
            logger.info("Navigating to the URL: " + websiteUrl);
            test.info("Navigating to the URL: " + websiteUrl);
            driver.get(websiteUrl);

            // Step 2: Verify that home page is visible successfully
            HomePage homePage = new HomePage(driver);
            logger.info("Verifying that home page is visible");
            test.info("Verifying that home page is visible");
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

            // Step 3: Click on 'Products' button
            logger.info("Clicking on 'Products' button");
            test.info("Clicking on 'Products' button");
            homePage.clickProducts();

            // Step 4: Verify user is navigated to ALL PRODUCTS page successfully
            ProductsPage productsPage = new ProductsPage(driver);
            logger.info("Verifying that 'ALL PRODUCTS' page is visible");
            test.info("Verifying that 'ALL PRODUCTS' page is visible");
            Assert.assertTrue(productsPage.isAllProductsPageVisible(), "ALL PRODUCTS page is not visible");

            // Step 5: Verify the products list is visible
            logger.info("Verifying that the products list is visible");
            test.info("Verifying that the products list is visible");
            Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible");

            // Step 6: Click on 'View Product' of the first product
            logger.info("Clicking on the first product");
            test.info("Clicking on the first product");
            productsPage.clickFirstProduct();

            // Step 7: Verify user is navigated to the product detail page
            ProductDetailPage productDetailPage = new ProductDetailPage(driver);
            logger.info("Verifying that the product detail page is visible");
            test.info("Verifying that the product detail page is visible");
            Assert.assertTrue(productDetailPage.isProductDetailPageVisible(), "Product detail page is not visible");

            // Step 8: Verify product details
            logger.info("Verifying product details");
            test.info("Verifying product details");
            Assert.assertTrue(productDetailPage.isProductNameVisible(), "Product Name: Blue Top not visible");
            Assert.assertTrue(productDetailPage.isProductCategoryVisible(), "Product Category: Women > Tops not visible");
            Assert.assertTrue(productDetailPage.isProductPriceVisible(), "Product Price: Rs. 500 not visible");
            Assert.assertTrue(productDetailPage.isProductAvailabilityVisible(), "Product Availability: In Stock not visible");
            Assert.assertTrue(productDetailPage.isProductConditionVisible(), "Product Condition: New not visible");
            Assert.assertTrue(productDetailPage.isProductBrandVisible(), "Product Brand: Polo not visible");

            logger.info("Test Completed Successfully: testAllProductsAndDetailPage");
            test.pass("Test Completed Successfully: testAllProductsAndDetailPage");

        } catch (AssertionError e) {
            captureScreenshot("testAllProductsAndDetailPage");
            logger.error("Test Failed: " + e.getMessage(), e);
            test.fail("Test Failed: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            captureScreenshot("testAllProductsAndDetailPage");
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
