package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By allProductsHeader = By.xpath("//h2[contains(text(),'All Products')]");
    private By productsList = By.xpath("/html/body/section[2]/div/div/div[2]/div");
    private By firstProductViewButton = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul");

    public boolean isAllProductsPageVisible() {
        return driver.findElement(allProductsHeader).isDisplayed();
    }

    public boolean isProductsListVisible() {
        return driver.findElement(productsList).isDisplayed();
    }

    public void clickFirstProduct() {
        driver.findElement(firstProductViewButton).click();
    }
}
