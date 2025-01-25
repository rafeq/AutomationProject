package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage {
    WebDriver driver;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    private By newUserSignupHeader = By.xpath("//h2[contains(text(),'New User Signup!')]");
    private By nameInput = By.xpath("//input[@name='name']");
    private By emailInput = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]");
    private By signupButton = By.xpath("//button[contains(text(),'Signup')]");

    public boolean isNewUserSignupVisible() {
        return driver.findElement(newUserSignupHeader).isDisplayed();
    }

    public void enterNameAndEmail(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickSignupButton() {
        driver.findElement(signupButton).click();
    }
}
