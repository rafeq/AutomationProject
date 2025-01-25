package testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.WebDriverManager;

public class LoginTest {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    String websiteUrl = ConfigReader.getWebsiteUrl();
    String browserType = ConfigReader.getBrowserType();
    @BeforeMethod
    public void setUp() {
        driver = WebDriverManager.getDriver();
        driver.get(websiteUrl);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
    }

    @Test
    public void testLoginAndDeleteAccount() {
        // Step 3: Verify that the home page is visible
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // Step 4: Click on 'Signup / Login' button
        homePage.clickSignupLogin();

        // Step 5: Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.isLoginPageVisible(), "'Login to your account' is not visible");

        // Step 6: Enter correct email and password
        loginPage.login("74john.doe@example.com", "password123");

        // Step 8: Verify 'Logged in as username' is visible
        Assert.assertTrue(loginPage.isLoggedInAsVisible(), "'Logged in as username' is not visible");

        // Step 9: Click 'Delete Account' button
        accountPage.clickDeleteAccount();

        // Step 10: Verify 'ACCOUNT DELETED!' is visible
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' is not visible");
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}

