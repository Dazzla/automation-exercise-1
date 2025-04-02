package WebDriverHelpers;
import org.openqa.selenium.WebDriver;

public class SharedDriver {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

}