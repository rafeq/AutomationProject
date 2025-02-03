package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class represents the Products Page of the application.
 * It provides methods to verify the visibility of product elements and interact with them.
 */
public class ProductsPage {
    WebDriver driver;

    /**
     * Constructor to initialize the WebDriver instance.
     *
     * @param driver The WebDriver instance used for browser interaction.
     */
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements on the Products page
    private final By allProductsHeader = By.xpath("//h2[contains(text(),'All Products')]");
    private final By productsList = By.xpath("/html/body/section[2]/div/div/div[2]/div");
    private final By firstProductViewButton = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul");

    /**
     * Checks if the "All Products" page is visible.
     *
     * @return true if the "All Products" header is displayed, otherwise false.
     */
    public boolean isAllProductsPageVisible() {
        return driver.findElement(allProductsHeader).isDisplayed();
    }

    /**
     * Checks if the products list is visible on the page.
     *
     * @return true if the products list is displayed, otherwise false.
     */
    public boolean isProductsListVisible() {
        return driver.findElement(productsList).isDisplayed();
    }

    /**
     * Clicks on the view button of the first product in the list.
     */
    public void clickFirstProduct() {
        driver.findElement(firstProductViewButton).click();
    }
}