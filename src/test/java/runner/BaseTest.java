package runner;

import WebDriverHelpers.SharedDriver;
import WebDriverHelpers.WebDriverParameterResolver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(WebDriverParameterResolver.class)
public abstract class BaseTest {

    protected WebDriver driver;
    private SharedDriver sharedDriver;

    @BeforeAll
    void setUp(SharedDriver sharedDriver) {
        this.sharedDriver = sharedDriver;
        this.driver = sharedDriver.getDriver();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    @AfterAll
    void tearDown() {
        if (sharedDriver != null) {
            sharedDriver.close();
            sharedDriver = null;
        }
        driver = null;
    }
}
