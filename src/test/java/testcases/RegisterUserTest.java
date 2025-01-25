package testcases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;
import pages.AccountPage;
import utils.ConfigReader;
import utils.ExcelReader;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
import java.util.Random;

public class RegisterUserTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RegisterUserTest.class);

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

        try {
            logger.info("Starting test: Register User");

            driver.get(websiteUrl);
            logger.info("Navigated to URL: " + websiteUrl);
            logger.info("Home page");
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
            homePage.clickSignupLogin();

            logger.info("New User Signup!");
            SignupPage signupPage = new SignupPage(driver);
            Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' is not visible");
            signupPage.enterNameAndEmail(name, modifiedEmail);

            signupPage.clickSignupButton();

            AccountPage accountPage = new AccountPage(driver);
            Assert.assertTrue(accountPage.isAccountInfoVisible(), "'Enter Account Information' is not visible");
            logger.info("Enter Account Information");
            accountPage.enterAccountInfo(password, name.split(" ")[0], name.split(" ")[1], address, country, state, city, zipcode, phone);
            accountPage.clickCreateAccount();

            Assert.assertTrue(accountPage.isAccountCreatedVisible(), "'ACCOUNT CREATED!' is not visible");
            logger.info("Test case executed successfully");

        } catch (AssertionError | Exception e) {
            logger.error("Test case failed: " + e.getMessage(), e);
            throw e;
        }
    }
}
