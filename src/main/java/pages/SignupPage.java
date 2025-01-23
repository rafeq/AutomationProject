package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage {
    private WebDriver driver;

    // Locators
    private By newUserSignupText = By.xpath("//h2[text()='New User Signup!']");
    private By nameInput = By.xpath("//input[@name='name']");
    private By emailInput = By.xpath("//input[@name='email']");
    private By signupButton = By.xpath("//button[contains(text(), 'Signup')]");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNewUserSignupVisible() {
        return driver.findElement(newUserSignupText).isDisplayed();
    }

    public void enterNameAndEmail(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }
}
