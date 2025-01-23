package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;
import pages.AccountPage;

public class RegisterUserTest extends BaseTest {

    @Test
    public void testRegisterUser() {
        driver.get("http://automationexercise.com");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        homePage.clickSignupLogin();

        SignupPage signupPage = new SignupPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' is not visible");

        signupPage.enterNameAndEmail("John Doe", "john.doe@example.com");
        signupPage.clickSignupButton();

        AccountPage accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.isAccountInfoVisible(), "'Enter Account Information' is not visible");

        accountPage.fillAccountDetails("password123", "John", "Doe");
        accountPage.clickCreateAccount();

        Assert.assertTrue(accountPage.isAccountCreatedVisible(), "'Account Created!' is not visible");

        accountPage.clickContinue();

        accountPage.clickDeleteAccount();
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'Account Deleted!' is not visible");
    }
}
