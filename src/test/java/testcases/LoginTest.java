package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.WebDriverManager;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.io.File;
import java.io.IOException;

public class LoginTest {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    String websiteUrl = ConfigReader.getWebsiteUrl();
    String browserType = ConfigReader.getBrowserType();
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    private static ExtentReports extentReports;
    private ExtentTest extentTest;

    // Initialize ExtentReports
    @BeforeMethod
    public void setUp() {
        // Setting up ExtentReports for each test method
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/LoginTestReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Login Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Tester", "Your Name");
        extentReports.setSystemInfo("Browser", browserType);

        driver = WebDriverManager.getDriver();
        driver.get(websiteUrl);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);

        logger.info("Test setup complete, navigating to the website.");
    }

    @Test
    public void testLoginAndDeleteAccount() {
        extentTest = extentReports.createTest("Login and Delete Account Test");
        try {
            // Step 3: Verify that the home page is visible
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
            extentTest.pass("Home page is visible.");
            logger.info("Home page is visible.");

            // Step 4: Click on 'Signup / Login' button
            homePage.clickSignupLogin();
            extentTest.info("Clicked on 'Signup / Login'.");
            logger.info("Clicked on 'Signup / Login'.");

            // Step 5: Verify 'Login to your account' is visible
            Assert.assertTrue(loginPage.isLoginPageVisible(), "'Login to your account' is not visible");
            extentTest.pass("'Login to your account' is visible.");
            logger.info("'Login to your account' is visible.");

            // Step 6: Enter correct email and password
            loginPage.login("74john.doe@example.com", "password123");
            extentTest.info("Login credentials entered.");
            logger.info("Login credentials entered.");

            // Step 8: Verify 'Logged in as username' is visible
            Assert.assertTrue(loginPage.isLoggedInAsVisible(), "'Logged in as username' is not visible");
            extentTest.pass("'Logged in as username' is visible.");
            logger.info("'Logged in as username' is visible.");

            // Step 9: Click 'Delete Account' button
            accountPage.clickDeleteAccount();
            extentTest.info("Clicked 'Delete Account'.");
            logger.info("Clicked 'Delete Account'.");

            // Step 10: Verify 'ACCOUNT DELETED!' is visible
            Assert.assertTrue(accountPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' is not visible");
            extentTest.pass("Account deleted successfully.");
            logger.info("Account deleted successfully.");
        } catch (Exception e) {
            logger.error("Error encountered during test execution", e);
            extentTest.fail("Test failed due to an exception",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("testLoginAndDeleteAccount_Failure")).build());
            Assert.fail("Test failed due to an exception");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String screenshotPath = takeScreenshot(result.getName() + "_Failure");
            extentTest.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
        WebDriverManager.quitDriver();
        logger.info("Test execution complete.");
        extentReports.flush();
    }

    private String takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
        try {
            FileHandler.copy(screenshot, new File(screenshotPath));
            logger.info("Screenshot taken for test: " + testName);
        } catch (IOException e) {
            logger.error("Failed to take screenshot", e);
        }
        return screenshotPath;
    }
}
