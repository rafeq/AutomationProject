package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import utils.ConfigReader;

public class AddToCartFromRecommendedItemsTest extends BaseTest {
    String websiteUrl = ConfigReader.getWebsiteUrl();
    String browserType = ConfigReader.getBrowserType();
    @Test
    public void testAddToCartFromRecommendedItems() {

        // Step 1: Navigate to the URL
        driver.get(websiteUrl);

        // Step 2: Verify that home page is visible successfully
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // Step 3: Scroll to bottom of page
        homePage.scrollToRecommendedItems();

        // Step 4: Verify 'RECOMMENDED ITEMS' are visible
        Assert.assertTrue(homePage.isRecommendedItemsVisible(), "RECOMMENDED ITEMS");

        // Step 5: Click on 'Add To Cart' on Recommended product
        homePage.clickAddToCartOnRecommendedItem();

        // Step 6: Click on 'View Cart' button
        homePage.clickViewCart();

        // Step 7: Verify that product is displayed in cart page
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isProductInCart(), "Product is not displayed in the cart page");
    }
}
