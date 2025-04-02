package WebDriverHelpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import WebDriverManager;

import java.time.Duration;

public class WebDriverFactory {
    private static WebDriver driver;

    private WebDriverFactory() {
        // Prevent instantiation
    }

    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}