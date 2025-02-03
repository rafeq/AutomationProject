package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class represents the Account Page of the application.
 * It provides methods to interact with elements such as creating an account,
 * entering account information, deleting an account, and verifying account-related actions.
 */
public class AccountPage {
    WebDriver driver;

    /**
     * Constructor to initialize the WebDriver instance.
     *
     * @param driver The WebDriver instance used for browser interaction.
     */
    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements on the Account page
    private final By accountInfoHeader = By.xpath("//*[@id=\"form\"]/div/div/div/div/h2");
    private final By createAccountButton = By.xpath("//button[contains(text(),'Create Account')]");
    private final By accountCreatedHeader = By.xpath("/html/body/section/div/div/div/h2");
    private final By continueButton = By.xpath("//a[contains(text(),'Continue')]");
    private final By deleteAccountButton = By.xpath("//a[contains(text(),'Delete Account')]");
    private final By accountDeletedHeader = By.xpath("/html/body/section/div/div/div/h2");

    /**
     * Checks if the account information section is visible.
     *
     * @return true if the account information section is displayed, otherwise false.
     */
    public boolean isAccountInfoVisible() {
        return driver.findElement(accountInfoHeader).isDisplayed();
    }

    /**
     * Fills in account information fields with user details.
     *
     * @param password     The user's password.
     * @param firstName    The user's first name.
     * @param lastName     The user's last name.
     * @param address      The user's address.
     * @param country      The user's country.
     * @param state        The user's state.
     * @param city         The user's city.
     * @param zipCode      The user's zip code.
     * @param mobileNumber The user's mobile number.
     */
    public void enterAccountInfo(String password, String firstName, String lastName, String address,
                                 String country, String state, String city, String zipCode, String mobileNumber) {
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"first_name\"]")).sendKeys(firstName);
        driver.findElement(By.xpath("//*[@id=\"last_name\"]")).sendKeys(lastName);
        driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys(address);
        driver.findElement(By.xpath("//*[@id=\"country\"]")).sendKeys(country);
        driver.findElement(By.xpath("//*[@id=\"state\"]")).sendKeys(state);
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(city);
        driver.findElement(By.xpath("//*[@id=\"zipcode\"]")).sendKeys(zipCode);
        driver.findElement(By.xpath("//*[@id=\"mobile_number\"]")).sendKeys(mobileNumber);
    }

    /**
     * Clicks the "Create Account" button to submit account details.
     */
    public void clickCreateAccount() {
        driver.findElement(createAccountButton).click();
    }

    /**
     * Checks if the "Account Created" confirmation message is visible.
     *
     * @return true if the confirmation message is displayed, otherwise false.
     */
    public boolean isAccountCreatedVisible() {
        return driver.findElement(accountCreatedHeader).isDisplayed();
    }

    /**
     * Clicks the "Continue" button after account creation.
     */
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    /**
     * Clicks the "Delete Account" button to remove the user's account.
     */
    public void clickDeleteAccount() {
        driver.findElement(deleteAccountButton).click();
    }

    /**
     * Checks if the "Account Deleted" confirmation message is visible.
     *
     * @return true if the confirmation message is displayed, otherwise false.
     */
    public boolean isAccountDeletedVisible() {
        return driver.findElement(accountDeletedHeader).isDisplayed();
    }
}
