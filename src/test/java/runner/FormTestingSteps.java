package runner;

import WebDriverHelpers.SharedDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import pages.SubmitFormPage;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class FormTestingSteps extends BaseTest{

    public static final String CONFIRMATION_MESSAGE = "Received!";

    public FormTestingSteps(SharedDriver sharedDriver) {
        this.driver = sharedDriver.getDriver();  // Initialize the driver from SharedDriver
    }


    public static final String EXPECTED_DD_SELECT_CONTENTS = "                Open this select menu\n" +
            "                One\n" +
            "                Two\n" +
            "                Three\n" +
            "              ";
    public static final String DEFAULT_PURPLE = "rgba(255, 255, 255, 1)";
    private WebDriver driver;
    private WebDriverWait wait;

    SubmitFormPage submitFormPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver"); //Make sure to configure
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Then("I see that the Text Input is empty")
    public void iSeeThatTheTextInputIsEmpty() {
        submitFormPage = new SubmitFormPage(driver);
        assertEquals("", submitFormPage.textInput.getText());
    }

    @And("the password field is empty")
    public void thePasswordFieldIsEmpty() {
        assertEquals("", submitFormPage.passwordInput.getText());
    }

    @And("the Textarea field is empty")
    public void theTextareaFieldIsBlank() {
        assertEquals("", submitFormPage.textAreaInput.getText());
    }

    @And("the Dropdown select is 'Open this select menu'")
    public void theDropdownSelectIsOpenThisSelectMenu() {
        assertEquals(EXPECTED_DD_SELECT_CONTENTS, submitFormPage.dropdownSelectInput.getText());
    }

    @And("the Dropdown datalist is blank")
    public void theDropdownDatalistIsBlank() {
        assertEquals("", submitFormPage.datalistInput.getText());
    }

    @And("the checked checkbox is checked")
    public void theCheckedCheckboxIsChecked() {
        assert( submitFormPage.checkedCheckboxInput.isSelected());


    }

    @And("the default checkbox is unchecked")
    public void theDefaultCheckboxIsUnchecked() {
        assertFalse( submitFormPage.defaultCheckboxInput.isSelected());
    }

    @And("the checked radio button is selected")
    public void theCheckedRadioButtonIsSelected() {
        assert(submitFormPage.checkedRadioInput.isSelected());
    }

    @And("the default radio button is unselected")
    public void theDefaultRadioButtonIsUnselected() {
        assertFalse( submitFormPage.uncheckedRadioInput.isSelected());
    }

    @And("the color picker is purple")
    public void theColorPickerIsPurple() {
        assertEquals(DEFAULT_PURPLE, submitFormPage.colorPickerInput.getCssValue("background-color"));

    }

    @And("the date picker is empty")
    public void theDatePickerIsEmpty() {
        assertEquals("", submitFormPage.datePickerInput.getText());

    }

    @And("the slider is centred")
    public void theSliderIsCentred() {
        assert(submitFormPage.sliderInput.getAttribute("value").contains("5"));

    }

    @And("tbe button is a Submit button")
    public void tbeButtonIsSubmit() {
        assert submitFormPage.submitButton.getAttribute("type").equals("submit");

    }

    @And("there is a link to the index page")
    public void theLinkIsToTheIndexPage() {
        submitFormPage.linkToIndexPage.click();
        assertEquals("https://www.selenium.dev/selenium/web/index.html", driver.getCurrentUrl());
    }

    @Given("I am on the form submission page")
    public void navigate_to_web_form() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @When("I submit the form with valid data")
    public void fill_form() {
        SubmitFormPage submitFormPage = new SubmitFormPage(driver);
        submitFormPage.completeFormAndSubmit();
    }

    @Then("I see a confirmation message")
    public void verify_success_message() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertEquals(CONFIRMATION_MESSAGE, message.getText());
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Resetting driver
        }
    }
}
