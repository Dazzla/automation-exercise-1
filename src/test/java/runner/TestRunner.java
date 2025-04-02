package runner;

import WebDriver.WebDriverFactory;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.jupiter.api.AfterAll;

@Cucumber
public class TestRunner {

    @AfterAll  // For closing after the test run
    public static void tearDown() {
        WebDriverFactory.quitDriver();
    }

}