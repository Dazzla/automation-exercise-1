package WebDriverHelpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    private WebDriverFactory() {}

    public static WebDriver getDriver() {

        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver"); // Set the actual path
        return new ChromeDriver();

    }
}