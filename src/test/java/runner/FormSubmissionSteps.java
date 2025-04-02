package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.SubmitFormPage;
import WebDriver.WebDriverFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class FormSubmissionSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    @Given("I am on the form submission page")
    public void navigate_to_web_form() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @When("I submit the form with valid data")
    public void fill_form() {
        SubmitFormPage submitFormPage = new SubmitFormPage(driver);
        submitFormPage.completeFormAndSubmit("Sample Name", "sample@example.com");
    }

    @Then("I see a confirmation message")
    public void verify_success_message() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertEquals("Received!", message.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}