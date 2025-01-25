package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private By accountInfoHeader = By.xpath("//*[@id=\"form\"]/div/div/div/div/h2");
    private By createAccountButton = By.xpath("//button[contains(text(),'Create Account')]");
    private By accountCreatedHeader = By.xpath("/html/body/section/div/div/div/h2");
    private By continueButton = By.xpath("//a[contains(text(),'Continue')]");
    private By deleteAccountButton = By.xpath("//a[contains(text(),'Delete Account')]");
    private By accountDeletedHeader = By.xpath("/html/body/section/div/div/div/h2");

    public boolean isAccountInfoVisible() {
        return driver.findElement(accountInfoHeader).isDisplayed();
    }

    public void enterAccountInfo(String password, String firstName, String lastName, String address, String country, String state, String city, String zipCode, String mobileNumber) {
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

    public void clickCreateAccount() {
        driver.findElement(createAccountButton).click();
    }

    public boolean isAccountCreatedVisible() {
        return driver.findElement(accountCreatedHeader).isDisplayed();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickDeleteAccount() {
        driver.findElement(deleteAccountButton).click();
    }

    public boolean isAccountDeletedVisible() {
        return driver.findElement(accountDeletedHeader).isDisplayed();
    }
}
