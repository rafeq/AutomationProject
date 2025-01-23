package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // Locators
    private By signupLoginButton = By.xpath("//a[contains(text(), 'Signup / Login')]");
    private By homePageVisible = By.xpath("//div[@class='carousel-inner']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageVisible() {
        return driver.findElement(homePageVisible).isDisplayed();
    }

    public void clickSignupLogin() {
        driver.findElement(signupLoginButton).click();
    }
}
