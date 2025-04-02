package WebDriverHelpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

public WebDriverFactory() {}

    public static WebDriverFactory create() {
        return new WebDriverFactory();
    }

    public static WebDriver createWebDriver() {
        return new ChromeDriver();
    }

    public static WebDriver getDriver() {

        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver"); // Set the actual path
        return new ChromeDriver();

    }
}