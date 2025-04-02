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
}