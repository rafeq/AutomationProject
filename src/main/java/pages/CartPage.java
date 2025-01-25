package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By cartPageHeader = By.xpath("//*[@id=\"cart_items\"]/div/div[1]/ol/li[2]");
    private By proceedToCheckoutButton = By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a");
    private By productInCart = By.xpath("//*[@id=\"cart_items\"]");


    public boolean isCartPageVisible() {
        return driver.findElement(cartPageHeader).isDisplayed();
    }

    public void clickProceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }

    public boolean isProductInCart() {
        return driver.findElement(productInCart).isDisplayed();
    }
}
