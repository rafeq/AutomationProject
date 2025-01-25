package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By signupLoginButton = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By homePageHeader = By.xpath("//h2[contains(text(),'Home')]");
    private By productsButton = By.xpath("//a[contains(text(),'Products')]");

    public void clickSignupLogin() {
        driver.findElement(signupLoginButton).click();
    }

    public boolean isHomePageVisible() {
        return driver.getTitle().contains("Automation Exercise");
    }
    public void clickProducts() {
        driver.findElement(productsButton).click();
    }
}
