package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class represents the Cart Page of the application.
 * It provides methods to interact with elements such as checking cart visibility,
 * proceeding to checkout, and verifying if a product is in the cart.
 */
public class CartPage {
    WebDriver driver;

    /**
     * Constructor to initialize the WebDriver instance.
     *
     * @param driver The WebDriver instance used for browser interaction.
     */
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements on the Cart page
    private final By cartPageHeader = By.xpath("//*[@id=\"cart_items\"]/div/div[1]/ol/li[2]");
    private final By proceedToCheckoutButton = By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a");
    private final By productInCart = By.xpath("//*[@id=\"cart_items\"]");

    /**
     * Checks if the cart page is visible by verifying the presence of the cart page header.
     *
     * @return true if the cart page header is displayed, otherwise false.
     */
    public boolean isCartPageVisible() {
        return driver.findElement(cartPageHeader).isDisplayed();
    }

    /**
     * Clicks the "Proceed to Checkout" button to navigate to the checkout process.
     */
    public void clickProceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }

    /**
     * Checks if a product is present in the shopping cart.
     *
     * @return true if a product is displayed in the cart, otherwise false.
     */
    public boolean isProductInCart() {
        return driver.findElement(productInCart).isDisplayed();
    }
}
