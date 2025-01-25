package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize wait here
    }

    private By signupLoginButton = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By homePageHeader = By.xpath("//h2[contains(text(),'Home')]");
    private By productsButton = By.xpath("//a[contains(text(),'Products')]");
    private By cartButton = By.xpath("//a[contains(text(),'Cart')]");
    private By firstProductAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[1]");
    private By loggedInAs = By.xpath("//b[contains(text(),'John Doe')]");
    private By recommendedItemsSection = By.xpath("//h2[text()='recommended items']");
    private By addToCartButton = By.xpath("/html/body/section[2]/div[1]/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/a");
    private By viewCartButton = By.xpath("/html/body/section[2]/div[1]/div/div[2]/div[1]/div[1]/div/div/div[2]/p[2]/a");

    public void clickSignupLogin() {
        driver.findElement(signupLoginButton).click();
    }

    public boolean isHomePageVisible() {
        return driver.getTitle().contains("Automation Exercise");
    }

    public void clickProducts() {
        driver.findElement(productsButton).click();
    }

    public void clickCart() {
        driver.findElement(cartButton).click();
    }

    public void addFirstProductToCart() {
        driver.findElement(firstProductAddToCart).click();
    }

    public boolean isLoggedInAsUsernameVisible(String username) {
        return driver.findElement(loggedInAs).getText().equals(username);
    }

    public void scrollToRecommendedItems() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement recommendedSection = driver.findElement(recommendedItemsSection);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", recommendedSection);
        wait.until(ExpectedConditions.visibilityOf(recommendedSection)); // Ensure it becomes visible
    }

    public boolean isRecommendedItemsVisible() {
        WebElement recommendedSection = wait.until(ExpectedConditions.visibilityOfElementLocated(recommendedItemsSection));
        return recommendedSection.isDisplayed();
    }

    public void clickAddToCartOnRecommendedItem() {
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
    }



    public void clickViewCart() {
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        viewCart.click();
    }
}
