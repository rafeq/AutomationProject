package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage {
    WebDriver driver;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productDetailHeader = By.xpath("/html/body/section/div/div/div[2]/div[2]");
    private By productName = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2");
    private By productCategory = By.xpath("//p[contains(text(),'Category')]");
    private By productPrice = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/span");
    private By productAvailability = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]");
    private By productCondition = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[3]");
    private By productBrand = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[4]");

    public boolean isProductDetailPageVisible() {
        return driver.findElement(productDetailHeader).isDisplayed();
    }

    public boolean isProductNameVisible() {
        return driver.findElement(productName).isDisplayed();
    }

    public boolean isProductCategoryVisible() {
        return driver.findElement(productCategory).isDisplayed();
    }

    public boolean isProductPriceVisible() {
        return driver.findElement(productPrice).isDisplayed();
    }

    public boolean isProductAvailabilityVisible() {
        return driver.findElement(productAvailability).isDisplayed();
    }

    public boolean isProductConditionVisible() {
        return driver.findElement(productCondition).isDisplayed();
    }

    public boolean isProductBrandVisible() {
        return driver.findElement(productBrand).isDisplayed();
    }
}
