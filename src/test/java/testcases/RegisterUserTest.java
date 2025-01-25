package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;
import pages.AccountPage;

import java.util.Random;

public class RegisterUserTest extends BaseTest {

    @Test
    public void testRegisterUser() {
        Random random = new Random();

        // Generate a random number between 50 (inclusive) and 100 (exclusive)
        int min = 50;
        int max = 100;
        int randomInRange = random.nextInt(max - min) + min;
        // Step 1: Navigate to URL
        driver.get("http://automationexercise.com");

        // Step 2: Verify that home page is visible
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // Step 3: Click 'Signup / Login' button
        homePage.clickSignupLogin();

        // Step 4: Verify 'New User Signup!' is visible
        SignupPage signupPage = new SignupPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' is not visible");

        // Step 5: Enter name and email
        signupPage.enterNameAndEmail("John Doe", randomInRange+"john.doe@example.com");

        // Step 6: Click 'Signup' button
        signupPage.clickSignupButton();

        // Step 7: Verify 'Enter Account Information' is visible
        AccountPage accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.isAccountInfoVisible(), "'Enter Account Information' is not visible");

        // Step 8: Fill account information and create account
        accountPage.enterAccountInfo("password123", "John", "Doe", "123 Test Street", "United States", "California", "Los Angeles", "90001", "1234567890");
        accountPage.clickCreateAccount();

        // Step 9: Verify 'Account Created!' is visible
        Assert.assertTrue(accountPage.isAccountCreatedVisible(), "'ACCOUNT CREATED!' is not visible");

        // Step 10: Click 'Continue'
       // accountPage.clickContinue();

        // Step 11: Delete account
       // accountPage.clickDeleteAccount();

        // Step 12: Verify 'Account Deleted!' is visible
        //Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' is not visible");
    }
}
