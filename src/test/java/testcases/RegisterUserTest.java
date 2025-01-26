package testcases;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AccountPage;
import pages.HomePage;
import pages.SignupPage;
import utils.ConfigReader;
import utils.ExcelReader;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RegisterUserTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RegisterUserTest.class);
    private static ExtentReports extentReports;
    private ExtentTest extentTest;

    // Initialize Extent Reports
    @BeforeClass
    public void setUpExtentReports() {
        // Setting up ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/RegisterUserTestReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Tester", "Your Name");
        extentReports.setSystemInfo("Browser", ConfigReader.getBrowserType());

        // Customize Report
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Register User Test");
        sparkReporter.config().setTheme(Theme.STANDARD);
    }

    @DataProvider(name = "RegisterData")
    public Object[][] getUserData() throws Exception {
        String filePath = "/Users/rafeqfiad/IdeaProjects/AutomationProject/src/test/resources/RegisterData.xlsx";
        String sheetName = "RegisterData";
        return ExcelReader.readExcelData(filePath, sheetName);
    }

    @Test(dataProvider = "RegisterData")
    public void testRegisterUser(String name, String email, String password, String address,
                                 String country, String state, String city, String zipcode, String phone) {

        String websiteUrl = ConfigReader.getWebsiteUrl();
        Random random = new Random();
        int randomInRange = random.nextInt(50) + 50;
        String modifiedEmail = email.replace("@", randomInRange + "@");

        extentTest = extentReports.createTest("Register User Test - " + name);
        logger.info("Test started: Register User Test - {}", name);

        try {
            // Test Steps
            logger.info("Navigating to URL: {}", websiteUrl);
            extentTest.info("Navigating to URL");
            driver.get(websiteUrl);
            extentTest.pass("Navigated to URL: " + websiteUrl);

            // Home Page Visibility
            logger.info("Validating Home Page visibility");
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
            extentTest.pass("Home page is visible");

            // Navigate to Signup Page
            logger.info("Clicking on 'Signup / Login' button");
            homePage.clickSignupLogin();
            extentTest.info("Clicked on 'Signup / Login' button");

            // Signup Page Validation
            logger.info("Validating 'New User Signup!' visibility");
            SignupPage signupPage = new SignupPage(driver);
            Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
            extentTest.pass("'New User Signup!' is visible");

            // Register User
            logger.info("Entering name and modified email");
            signupPage.enterNameAndEmail(name, modifiedEmail);
            extentTest.info("Entered name and email: " + name + " - " + modifiedEmail);

            logger.info("Clicking on 'Signup' button");
            signupPage.clickSignupButton();
            extentTest.info("Clicked 'Signup' button");

            // Account Page Validation
            logger.info("Validating 'Enter Account Information' visibility");
            AccountPage accountPage = new AccountPage(driver);
            Assert.assertTrue(accountPage.isAccountInfoVisible(), "'Enter Account Information' is not visible");
            extentTest.pass("'Enter Account Information' is visible");

            // Enter Account Information
            logger.info("Entering account information");
            accountPage.enterAccountInfo(password, name.split(" ")[0], name.split(" ")[1], address, country, state, city, zipcode, phone);
            extentTest.info("Entered account information");

            // Create Account
            logger.info("Clicking on 'Create Account' button");
            accountPage.clickCreateAccount();
            extentTest.info("Clicked 'Create Account' button");

            // Account Created Validation
            logger.info("Validating 'ACCOUNT CREATED!' visibility");
            Assert.assertTrue(accountPage.isAccountCreatedVisible(), "'ACCOUNT CREATED!' is not visible");
            extentTest.pass("'ACCOUNT CREATED!' is visible");

            // Final Success
            logger.info("Test case executed successfully");
            extentTest.pass("Test case executed successfully");

        } catch (AssertionError | Exception e) {
            logger.error("Test case failed: {}", e.getMessage(), e);
            extentTest.fail("Test case failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(attachScreenshot()).build());
            throw e;  // Rethrow the exception so that TestNG catches it
        }
    }

    // Method to take screenshots
    @Override
    protected String attachScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String screenshotPath =  "/screenshots/" + "RegisterUserTest" + ".png";

        try {
            FileHandler.copy(source, new File(screenshotPath));
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
            logger.info("Screenshot captured: {}", screenshotPath);
        } catch (IOException ioException) {
            logger.error("Failed to save screenshot: {}", ioException.getMessage(), ioException);
        }

        return screenshotPath;
    }

    // After each method, capture failure screenshot and log it
    @AfterMethod
    public void tearDown() {
        logger.info("Test execution complete");
        if (driver != null) {
            driver.quit();
        }
    }

    // After all tests, flush the reports
    @AfterClass
    public void tearDownExtentReports() {
        extentReports.flush();
    }
}
