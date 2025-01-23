package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private WebDriver driver;

    // Locators
    private By accountInfoText = By.xpath("//h2[text()='Enter Account Information']");
    private By passwordInput = By.xpath("//input[@name='password']");
    private By newsletterCheckbox = By.xpath("//input[@name='newsletter']");
    private By offersCheckbox = By.xpath("//input[@name='optin']");
    private By firstNameInput = By.xpath("//input[@name='first_name']");
    private By lastNameInput = By.xpath("//input[@name='last_name']");
    private By createAccountButton = By.xpath("//button[contains(text(), 'Create Account')]");
    private By accountCreatedText = By.xpath("//h2[text()='Account Created!']");
    private By continueButton = By.xpath("//a[contains(text(), 'Continue')]");
    private By deleteAccountButton = By.xpath("//a[contains(text(), 'Delete Account')]");
    private By accountDeletedText = By.xpath("//h2[text()='Account Deleted!']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAccountInfoVisible() {
        return driver.findElement(accountInfoText).isDisplayed();
    }

    public void fillAccountDetails(String password, String firstName, String lastName) {
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(newsletterCheckbox).click();
        driver.findElement(offersCheckbox).click();
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountButton).click();
    }

    public boolean isAccountCreatedVisible() {
        return driver.findElement(accountCreatedText).isDisplayed();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickDeleteAccount() {
        driver.findElement(deleteAccountButton).click();
    }

    public boolean isAccountDeletedVisible() {
        return driver.findElement(accountDeletedText).isDisplayed();
    }
}
