package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class represents the Checkout Page of the application.
 * It provides methods to interact with checkout elements, such as entering comments,
 * placing an order, and making a payment.
 */
public class CheckoutPage {
    WebDriver driver;

    /**
     * Constructor to initialize the WebDriver instance.
     *
     * @param driver The WebDriver instance used for browser interaction.
     */
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements on the Checkout page
    private final By addressDetails = By.xpath("//h2[contains(text(),'Address Details')]");
    private final By orderReview = By.xpath("//h2[contains(text(),'Review Your Order')]");
    private final By commentTextArea = By.xpath("/html/body/section/div/div[6]/textarea");
    private final By placeOrderButton = By.xpath("/html/body/section/div/div[7]/a");
    private final By paymentNameOnCard = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[1]/div/input");
    private final By paymentCardNumber = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[2]/div/input");
    private final By paymentCVC = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[3]/div[1]/input");
    private final By paymentExpiration = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[3]/div[2]/input");
    private final By paymentYear = By.xpath("/html/body/section/div/div[3]/div/div[2]/form/div[3]/div[3]/input");
    private final By payAndConfirmButton = By.xpath("//*[@id=\"submit\"]");
    private final By successMessage = By.xpath("/html/body/section/div/div/div/h2");

    /**
     * Checks if the address details section is visible on the checkout page.
     *
     * @return true if the address details section is displayed, otherwise false.
     */
    public boolean isAddressDetailsVisible() {
        return driver.findElement(addressDetails).isDisplayed();
    }

    /**
     * Checks if the order review section is visible on the checkout page.
     *
     * @return true if the order review section is displayed, otherwise false.
     */
    public boolean isOrderReviewVisible() {
        return driver.findElement(orderReview).isDisplayed();
    }

    /**
     * Enters a comment in the provided text area on the checkout page.
     *
     * @param comment The comment text to be entered.
     */
    public void enterComment(String comment) {
        driver.findElement(commentTextArea).sendKeys(comment);
    }

    /**
     * Clicks the "Place Order" button to proceed with the checkout process.
     */
    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
    }

    /**
     * Enters payment details including cardholder name, card number, CVC, expiration month, and expiration year.
     *
     * @param name               The name on the card.
     * @param cardNumber         The card number.
     * @param cvc                The card's CVC code.
     * @param monthExpirationDate The expiration month of the card.
     * @param YearExpirationDate The expiration year of the card.
     */
    public void enterPaymentDetails(String name, String cardNumber, String cvc, String monthExpirationDate, String YearExpirationDate) {
        driver.findElement(paymentNameOnCard).sendKeys(name);
        driver.findElement(paymentCardNumber).sendKeys(cardNumber);
        driver.findElement(paymentCVC).sendKeys(cvc);
        driver.findElement(paymentExpiration).sendKeys(monthExpirationDate);
        driver.findElement(paymentYear).sendKeys(YearExpirationDate);
    }

    /**
     * Clicks the "Pay and Confirm Order" button to complete the payment process.
     */
    public void clickPayAndConfirmOrder() {
        driver.findElement(payAndConfirmButton).click();
    }

    /**
     * Checks if the order success message is visible after completing the payment.
     *
     * @return true if the success message is displayed, otherwise false.
     */
    public boolean isOrderSuccessMessageVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }
}
