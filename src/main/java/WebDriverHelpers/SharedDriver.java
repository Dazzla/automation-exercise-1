package WebDriverHelpers;

import org.openqa.selenium.WebDriver;

public class SharedDriver implements AutoCloseable {

    private WebDriver driver;

    public synchronized WebDriver getDriver() {
        if (driver == null) {
            driver = WebDriverFactory.createWebDriver();
        }
        return driver;
    }

    public synchronized boolean isInitialised() {
        return driver != null;
    }

    @Override
    public synchronized void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
