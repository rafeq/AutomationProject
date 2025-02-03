package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class represents the Login Page of the application.
 * It provides methods to interact with login fields and verify login status.
 */
public class LoginPage {
    WebDriver driver;

    /**
     * Constructor to initialize the WebDriver instance.
     *
     * @param driver The WebDriver instance used for browser interaction.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements on the Login page
    private final By loginToYourAccountText = By.xpath("//h2[contains(text(),'Login to your account')]");
    private final By emailField = By.name("email");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[contains(text(),'Login')]");
    private final By loggedInAsUsername = By.xpath("//a[contains(text(),'Logged in as')]");

    /**
     * Checks if the login page is visible by verifying the presence of the "Login to your account" text.
     *
     * @return true if the login page is displayed, otherwise false.
     */
    public boolean isLoginPageVisible() {
        return driver.findElement(loginToYourAccountText).isDisplayed();
    }

    /**
     * Logs in to the application using the provided email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     */
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    /**
     * Checks if the "Logged in as" text is visible after a successful login.
     *
     * @return true if the user is logged in, otherwise false.
     */
    public boolean isLoggedInAsVisible() {
        return driver.findElement(loggedInAsUsername).isDisplayed();
    }
}
