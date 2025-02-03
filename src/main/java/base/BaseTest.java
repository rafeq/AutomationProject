package base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        System.setProperty("testName", this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[2].getMethodName());
        System.setProperty("webdriver.gecko.driver", "/Users/rafeqfiad/Downloads/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    protected String attachScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Define the screenshot folder path
        String screenshotDirPath = System.getProperty("user.dir") + "/screenshots";
        File screenshotDir = new File(screenshotDirPath);

        // Check and create the folder if it doesn't exist
        if (!screenshotDir.exists()) {
            screenshotDir.mkdir(); // Create the directory
        }

        // Define the full screenshot file path
        String screenshotPath = screenshotDirPath + "/" + System.currentTimeMillis() + ".png";

        try {
            FileHandler.copy(source, new File(screenshotPath));
            logger.info("Screenshot captured: " + screenshotPath);
        } catch (IOException ioException) {
            logger.error("Failed to save screenshot: " + ioException.getMessage(), ioException);
        }

        return screenshotPath;
    }

}



// Initialize ExcelUtils with the Excel file path and sheet name
//excel = new ExcelUtils("/Users/rafeqfiad/IdeaProjects/AutomationProject/src/test/resources/RegisterData.xlsx", "RegisterData");
//    @Test
//    public void testRegisterUser() {
//        try {
//            Random random = new Random();
//
//            // Generate a random number between 50 (inclusive) and 100 (exclusive)
//            int min = 50;
//            int max = 100;
//            int randomInRange = random.nextInt(max - min) + min;
//
//            // Step 1: Navigate to URL
//            driver.get("http://automationexercise.com");
//
//            // Step 2: Verify home page is visible
//            if (!driver.getTitle().contains("Automation Exercise")) {
//                throw new AssertionError("Home page is not visible");
//            }
//
//            // Step 3: Click on 'Signup / Login' button
//            WebElement signupLoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login")));
//            signupLoginButton.click();
//
//            // Step 4: Verify 'New User Signup!' is visible
//            WebElement newUserSignupText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New User Signup!']")));
//            if (!newUserSignupText.isDisplayed()) {
//                throw new AssertionError("'New User Signup!' is not visible");
//            }
//
//            // Step 5: Fetch data from Excel and enter name and email address
//           // System.out.println("excel"+excel);
//            String name = excel.getCellData(1, 0);  // Row 1, Column 0
//            String email = excel.getCellData(1, 1); // Row 1, Column 1
//            email = randomInRange + email;
//            driver.findElement(By.name("name")).sendKeys(name);
//            driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(email);
//
//            // Step 6: Click 'Signup' button
//            driver.findElement(By.xpath("//button[text()='Signup']")).click();
//
////            // Step 7: Verify 'ENTER ACCOUNT INFORMATION' is visible
////            WebElement accountInfoText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Enter Account Information']")));
////            if (!accountInfoText.isDisplayed()) {
////                throw new AssertionError("'ENTER ACCOUNT INFORMATION' is not visible");
////            }
//
//            // Step 8: Fill details using Excel data
//            driver.findElement(By.id("id_gender1")).click(); // Select Title 'Mr.'
//            driver.findElement(By.id("password")).sendKeys(excel.getCellData(1, 2)); // Password
//            new Select(driver.findElement(By.id("days"))).selectByValue("1");
//            new Select(driver.findElement(By.id("months"))).selectByValue("1");
//            new Select(driver.findElement(By.id("years"))).selectByValue("2000");
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//            // Step 9: Select checkboxes
//            driver.findElement(By.id("newsletter")).click();
//            driver.findElement(By.id("optin")).click();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//            // Step 10: Fill personal details
//            driver.findElement(By.id("first_name")).sendKeys(excel.getCellData(1, 4)); // first name
//            driver.findElement(By.id("last_name")).sendKeys(excel.getCellData(1, 5));    // last name
//            driver.findElement(By.id("address1")).sendKeys(excel.getCellData(1, 6));    // adress
//            driver.findElement(By.id("country")).sendKeys(excel.getCellData(1, 7));   // country
//            driver.findElement(By.id("state")).sendKeys(excel.getCellData(1, 8)); // state
//            driver.findElement(By.id("city")).sendKeys(excel.getCellData(1, 9)); // city
//            driver.findElement(By.id("zipcode")).sendKeys(excel.getCellData(1, 10)); // zip code
//            driver.findElement(By.id("mobile_number")).sendKeys(excel.getCellData(1, 11)); // Mobile
//            driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/form/button")).click();
//            WebElement text = driver.findElement(By.xpath("/html/body/section/div/div/div/h2"));
//            System.out.println(text.getText());
//            if (text.getText().equals("ACCOUNT CREATED!")){
//                System.out.println("Sucsess");
//            }else {
//                System.out.println("Fail");
//            }
//            // Proceed with the remaining steps...
//        } finally {
//            //driver.quit(); // Close the browser
//        }
//    }
//
// //   @AfterMethod
////    public void tearDown() {
////        if (driver != null) {
////            driver.quit();
////        }
////    }

