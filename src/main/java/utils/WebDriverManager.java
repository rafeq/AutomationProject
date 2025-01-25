package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;
    protected static WebDriverWait wait;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.gecko.driver", "/Users/rafeqfiad/Downloads/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize wait after driver
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
