package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addressDetails = By.xpath("//h2[contains(text(),'Address Details')]");
    private By orderReview = By.xpath("//h2[contains(text(),'Review Your Order')]");
    private By commentTextArea = By.xpath("/html/body/section/div/div[6]/textarea");
    private By placeOrderButton = By.xpath("/html/body/section/div/div[7]/a");
    private By paymentNameOnCard = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[1]/div/input");
    private By paymentCardNumber = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[2]/div/input");
    private By paymentCVC = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[3]/div[1]/input");
    private By paymentExpiration = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[3]/div[2]/input");
    private By paymentYear = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[3]/div[3]/input");
    private By payAndConfirmButton = By.xpath("//*[@id=\"submit\"]");
    private By successMessage = By.xpath("/html/body/section/div/div/div/h2");

    public boolean isAddressDetailsVisible() {
        return driver.findElement(addressDetails).isDisplayed();
    }

    public boolean isOrderReviewVisible() {
        return driver.findElement(orderReview).isDisplayed();
    }

    public void enterComment(String comment) {
        driver.findElement(commentTextArea).sendKeys(comment);
    }

    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
    }

    public void enterPaymentDetails(String name, String cardNumber, String cvc, String monthExpirationDate,String YearExpirationDate) {
        driver.findElement(paymentNameOnCard).sendKeys(name);
        driver.findElement(paymentCardNumber).sendKeys(cardNumber);
        driver.findElement(paymentCVC).sendKeys(cvc);
        driver.findElement(paymentExpiration).sendKeys(monthExpirationDate);
        driver.findElement(paymentYear).sendKeys(YearExpirationDate);
    }

    public void clickPayAndConfirmOrder() {
        driver.findElement(payAndConfirmButton).click();
    }

    public boolean isOrderSuccessMessageVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }
}
