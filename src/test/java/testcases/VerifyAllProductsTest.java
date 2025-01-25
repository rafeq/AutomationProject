package testcases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.ProductDetailPage;
import utils.ConfigReader;
import utils.ExcelUtils;

public class VerifyAllProductsTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(VerifyAllProductsTest.class);
   // private static final excel = new ExcelUtils("/Users/rafeqfiad/IdeaProjects/AutomationProject/src/test/resources/RegisterData.xlsx", "RegisterData");

    @Test
    public void testAllProductsAndDetailPage() {

        // Fetching the website URL and browser type from the config file
        String websiteUrl = ConfigReader.getWebsiteUrl();
        String browserType = ConfigReader.getBrowserType();

        logger.info("Test Started: testAllProductsAndDetailPage");
        //String name = excel.getCellData(1, 0);
        try {
            // Step 1: Navigate to the dynamic URL
            logger.info("Navigating to the URL: " + websiteUrl);
            driver.get(websiteUrl);

            // Step 2: Verify that home page is visible successfully
            HomePage homePage = new HomePage(driver);
            logger.info("Verifying that home page is visible");
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

            // Step 3: Click on 'Products' button
            logger.info("Clicking on 'Products' button");
            homePage.clickProducts();

            // Step 4: Verify user is navigated to ALL PRODUCTS page successfully
            ProductsPage productsPage = new ProductsPage(driver);
            logger.info("Verifying that 'ALL PRODUCTS' page is visible");
            Assert.assertTrue(productsPage.isAllProductsPageVisible(), "ALL PRODUCTS page is not visible");

            // Step 5: Verify the products list is visible
            logger.info("Verifying that the products list is visible");
            Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible");

            // Step 6: Click on 'View Product' of the first product
            logger.info("Clicking on the first product");
            productsPage.clickFirstProduct();

            // Step 7: Verify user is navigated to the product detail page
            ProductDetailPage productDetailPage = new ProductDetailPage(driver);
            logger.info("Verifying that the product detail page is visible");
            Assert.assertTrue(productDetailPage.isProductDetailPageVisible(), "Product detail page is not visible");

            // Step 8: Verify product details
            logger.info("Verifying product details");
            Assert.assertTrue(productDetailPage.isProductNameVisible(), "Product Name: Blue Top not visible");
            Assert.assertTrue(productDetailPage.isProductCategoryVisible(), "Product Category: Women > Tops not visible");
            Assert.assertTrue(productDetailPage.isProductPriceVisible(), "Product Price: Rs. 500 not visible");
            Assert.assertTrue(productDetailPage.isProductAvailabilityVisible(), "Product Availability: In Stock not visible");
            Assert.assertTrue(productDetailPage.isProductConditionVisible(), "Product Condition: New not visible");
            Assert.assertTrue(productDetailPage.isProductBrandVisible(), "Product Brand: Polo not visible");

            logger.info("Test Completed Successfully: testAllProductsAndDetailPage");

        } catch (AssertionError e) {
            logger.error("Test Failed: " + e.getMessage(), e);
            throw e;  // Re-throw the exception to make the test fail

        } catch (Exception e) {
            logger.error("Unexpected Error: " + e.getMessage(), e);
            throw e;  // Re-throw the exception to make the test fail
        }
    }
}
