package WebDriverHelpers;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class DriverHooks {

    private final SharedDriver sharedDriver;

    public DriverHooks(SharedDriver sharedDriver) {
        this.sharedDriver = sharedDriver;
    }

    @Before
    public void setup() {
        WebDriver driver = WebDriverFactory.getDriver();
        this.sharedDriver.setDriver(driver);
    }

    @After
    public void tearDown() {
        WebDriver driver = sharedDriver.getDriver();
        if (driver != null) {
            driver.quit();
            sharedDriver.setDriver(null); // Crucial: Reset sharedDriver after quit
        }
    }
}
