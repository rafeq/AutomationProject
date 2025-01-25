package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.ProductDetailPage;

public class VerifyAllProductsTest extends BaseTest {

    @Test
    public void testAllProductsAndDetailPage() {
        // Step 1: Navigate to the URL
        driver.get("http://automationexercise.com");

        // Step 2: Verify that home page is visible successfully
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // Step 3: Click on 'Products' button
        homePage.clickProducts();

        // Step 4: Verify user is navigated to ALL PRODUCTS page successfully
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "ALL PRODUCTS page is not visible");

        // Step 5: Verify the products list is visible
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible");

        // Step 6: Click on 'View Product' of the first product
        productsPage.clickFirstProduct();

        // Step 7: Verify user is navigated to the product detail page
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue(productDetailPage.isProductDetailPageVisible(), "Product detail page is not visible");

        // Step 8: Verify product details
        Assert.assertTrue(productDetailPage.isProductNameVisible(), "Blue Top");
        Assert.assertTrue(productDetailPage.isProductCategoryVisible(), "Women > Tops");
        Assert.assertTrue(productDetailPage.isProductPriceVisible(), "Rs. 500");
        Assert.assertTrue(productDetailPage.isProductAvailabilityVisible(), "Availability: In Stock");
        Assert.assertTrue(productDetailPage.isProductConditionVisible(), "Condition: New");
        Assert.assertTrue(productDetailPage.isProductBrandVisible(), "Brand: Polo");
    }
}
