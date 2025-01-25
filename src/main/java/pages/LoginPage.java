package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginToYourAccountText = By.xpath("//h2[contains(text(),'Login to your account')]");
    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");
    private By loggedInAsUsername = By.xpath("//a[contains(text(),'Logged in as')]");

    public boolean isLoginPageVisible() {
        return driver.findElement(loginToYourAccountText).isDisplayed();
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isLoggedInAsVisible() {
        return driver.findElement(loggedInAsUsername).isDisplayed();
    }
}
