package WebDriverHelpers;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import WebDriverHelpers.WebDriverFactory;

public class DriverHooks {

    private final SharedDriver sharedDriver;  // Dependency Injection

    public DriverHooks(SharedDriver sharedDriver) {  // Constructor Injection
        this.sharedDriver = sharedDriver;
    }

    @Before
    public void setup() {
        WebDriver driver = WebDriverFactory.getDriver();
        this.sharedDriver.setDriver(driver); //Set the driver here.
    }

    @After
    public void tearDown() {
        WebDriver driver = sharedDriver.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
