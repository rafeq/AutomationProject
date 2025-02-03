package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class represents the Signup Page of the application and provides methods
 * to interact with the signup form elements.
 */
public class SignupPage {
    WebDriver driver;

    /**
     * Constructor to initialize the WebDriver instance.
     *
     * @param driver The WebDriver instance to interact with the browser.
     */
    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements on the signup page
    private final By newUserSignupHeader = By.xpath("//h2[contains(text(),'New User Signup!')]");
    private final By nameInput = By.xpath("//input[@name='name']");
    private final By emailInput = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]");
    private final By signupButton = By.xpath("//button[contains(text(),'Signup')]");

    /**
     * Checks if the "New User Signup" header is visible on the page.
     *
     * @return true if the header is displayed, otherwise false.
     */
    public boolean isNewUserSignupVisible() {
        return driver.findElement(newUserSignupHeader).isDisplayed();
    }

    /**
     * Enters the provided name and email into the signup form fields.
     *
     * @param name  The name of the new user.
     * @param email The email address of the new user.
     */
    public void enterNameAndEmail(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
    }

    /**
     * Clicks the "Signup" button to submit the signup form.
     */
    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }
}
