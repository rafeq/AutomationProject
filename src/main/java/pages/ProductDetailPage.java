package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class represents the Product Detail Page of the application.
 * It provides methods to verify the visibility of product details.
 */
public class ProductDetailPage {
    WebDriver driver;

    /**
     * Constructor to initialize the WebDriver instance.
     *
     * @param driver The WebDriver instance used for browser interaction.
     */
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements on the product detail page
    private final By productDetailHeader = By.xpath("/html/body/section/div/div/div[2]/div[2]");
    private final By productName = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2");
    private final By productCategory = By.xpath("//p[contains(text(),'Category')]");
    private final By productPrice = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/span");
    private final By productAvailability = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]");
    private final By productCondition = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[3]");
    private final By productBrand = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[4]");

    /**
     * Checks if the Product Detail Page is visible.
     *
     * @return true if the product detail header is displayed, otherwise false.
     */
    public boolean isProductDetailPageVisible() {
        return driver.findElement(productDetailHeader).isDisplayed();
    }

    /**
     * Checks if the product name is visible on the page.
     *
     * @return true if the product name is displayed, otherwise false.
     */
    public boolean isProductNameVisible() {
        return driver.findElement(productName).isDisplayed();
    }

    /**
     * Checks if the product category is visible on the page.
     *
     * @return true if the product category is displayed, otherwise false.
     */
    public boolean isProductCategoryVisible() {
        return driver.findElement(productCategory).isDisplayed();
    }

    /**
     * Checks if the product price is visible on the page.
     *
     * @return true if the product price is displayed, otherwise false.
     */
    public boolean isProductPriceVisible() {
        return driver.findElement(productPrice).isDisplayed();
    }

    /**
     * Checks if the product availability status is visible on the page.
     *
     * @return true if the product availability is displayed, otherwise false.
     */
    public boolean isProductAvailabilityVisible() {
        return driver.findElement(productAvailability).isDisplayed();
    }

    /**
     * Checks if the product condition is visible on the page.
     *
     * @return true if the product condition is displayed, otherwise false.
     */
    public boolean isProductConditionVisible() {
        return driver.findElement(productCondition).isDisplayed();
    }

    /**
     * Checks if the product brand is visible on the page.
     *
     * @return true if the product brand is displayed, otherwise false.
     */
    public boolean isProductBrandVisible() {
        return driver.findElement(productBrand).isDisplayed();
    }
}
