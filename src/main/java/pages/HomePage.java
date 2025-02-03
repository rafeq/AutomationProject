package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * HomePage class represents the homepage of the Automation Exercise website.
 * It provides methods to interact with various elements on the homepage.
 */
public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Constructor for HomePage.
     * Initializes the WebDriver and WebDriverWait.
     *
     * @param driver WebDriver instance
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize wait
    }

    // Locators for elements on the home page
    private final By signupLoginButton = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By productsButton = By.xpath("//a[contains(text(),'Products')]");
    private final By cartButton = By.xpath("//a[contains(text(),'Cart')]");
    private final By firstProductAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[1]");
    private final By loggedInAs = By.xpath("//b[contains(text(),'John Doe')]");
    private final By recommendedItemsSection = By.xpath("//h2[text()='recommended items']");
    private final By addToCartButton = By.xpath("/html/body/section[2]/div[1]/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/a");
    private final By viewCartButton = By.xpath("/html/body/section[2]/div[1]/div/div[2]/div[1]/div[1]/div/div/div[2]/p[2]/a");

    /**
     * Clicks on the "Signup / Login" button.
     */
    public void clickSignupLogin() {
        driver.findElement(signupLoginButton).click();
    }

    /**
     * Checks if the homepage is visible by verifying the title.
     *
     * @return true if the page title contains "Automation Exercise", false otherwise.
     */
    public boolean isHomePageVisible() {
        return driver.getTitle().contains("Automation Exercise");
    }

    /**
     * Clicks on the "Products" button to navigate to the products page.
     */
    public void clickProducts() {
        driver.findElement(productsButton).click();
    }

    /**
     * Clicks on the "Cart" button to navigate to the cart page.
     */
    public void clickCart() {
        driver.findElement(cartButton).click();
    }

    /**
     * Adds the first product in the product list to the cart.
     */
    public void addFirstProductToCart() {
        driver.findElement(firstProductAddToCart).click();
    }

    /**
     * Checks if the "Logged in as [username]" section is visible.
     *
     * @param username The expected username
     * @return true if the username matches, false otherwise.
     */
    public boolean isLoggedInAsUsernameVisible(String username) {
        return driver.findElement(loggedInAs).getText().equals(username);
    }

    /**
     * Scrolls down to the "Recommended Items" section on the homepage.
     */
    public void scrollToRecommendedItems() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement recommendedSection = driver.findElement(recommendedItemsSection);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", recommendedSection);
        wait.until(ExpectedConditions.visibilityOf(recommendedSection));
    }

    /**
     * Checks if the "Recommended Items" section is visible on the homepage.
     *
     * @return true if the recommended items section is visible, false otherwise.
     */
    public boolean isRecommendedItemsVisible() {
        WebElement recommendedSection = wait.until(ExpectedConditions.visibilityOfElementLocated(recommendedItemsSection));
        return recommendedSection.isDisplayed();
    }

    /**
     * Clicks on the "Add to Cart" button for the first recommended item.
     */
    public void clickAddToCartOnRecommendedItem() {
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
    }

    /**
     * Clicks on the "View Cart" button to navigate to the cart page.
     */
    public void clickViewCart() {
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        viewCart.click();
    }
}
