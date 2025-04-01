package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class FormSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("the user navigates to the web form")
    public void navigate_to_web_form() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @When("the user fills out the form with valid data")
    public void fill_form() {
        driver.findElement(By.id("my-text-id")).sendKeys("Test User");
        driver.findElement(By.name("my-password")).sendKeys("password123");
        driver.findElement(By.name("my-textarea")).sendKeys("Testing Selenium and Cucumber integration.");
        driver.findElement(By.cssSelector("select[name='my-select'] option[value='1']")).click();
        driver.findElement(By.id("my-check-1")).click();
    }

    @When("submits the form")
    public void submit_form() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("the form should display a success message")
    public void verify_success_message() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertEquals("Received!", message.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}