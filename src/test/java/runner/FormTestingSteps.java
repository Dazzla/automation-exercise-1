package runner;

import WebDriverHelpers.SharedDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SubmitFormPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FormTestingSteps extends BaseTest {

    public static final String CONFIRMATION_MESSAGE = "Received!";

    public static final String EXPECTED_DD_SELECT_CONTENTS = """
                            Open this select menu
                            One
                            Two
                            Three
                          \
            """;
    public static final String DEFAULT_PURPLE = "rgba(255, 255, 255, 1)";

    private final SharedDriver sharedDriver;
    private WebDriverWait wait;
    private SubmitFormPage submitFormPage;

    public FormTestingSteps(SharedDriver sharedDriver) {
        this.sharedDriver = sharedDriver;
    }

    @Before
    public void setUpScenario() {
        driver = sharedDriver.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on the form submission page")
    public void navigate_to_web_form() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        submitFormPage = new SubmitFormPage(driver);
    }

    @Then("I see that the Text Input is empty")
    public void iSeeThatTheTextInputIsEmpty() {
        assertTrue(page().isTextInputEmpty());
    }

    @And("the password field is empty")
    public void thePasswordFieldIsEmpty() {
        assertTrue(page().isPasswordInputEmpty());
    }

    @And("the Textarea field is empty")
    public void theTextareaFieldIsBlank() {
        assertTrue(page().isTextAreaEmpty());
    }

    @And("the Dropdown select is 'Open this select menu'")
    public void theDropdownSelectIsOpenThisSelectMenu() {
        assertEquals(EXPECTED_DD_SELECT_CONTENTS, page().dropdownContents());
    }

    @And("the Dropdown datalist is blank")
    public void theDropdownDatalistIsBlank() {
        assertTrue(page().isDatalistEmpty());
    }

    @And("the checked checkbox is checked")
    public void theCheckedCheckboxIsChecked() {
        assertTrue(page().isCheckedCheckboxSelected());
    }

    @And("the default checkbox is unchecked")
    public void theDefaultCheckboxIsUnchecked() {
        assertFalse(page().isDefaultCheckboxSelected());
    }

    @And("the checked radio button is selected")
    public void theCheckedRadioButtonIsSelected() {
        assertTrue(page().isCheckedRadioSelected());
    }

    @And("the default radio button is unselected")
    public void theDefaultRadioButtonIsUnselected() {
        assertFalse(page().isDefaultRadioSelected());
    }

    @And("the color picker is purple")
    public void theColorPickerIsPurple() {
        assertEquals(DEFAULT_PURPLE, page().colorPickerBackground());
    }

    @And("the date picker is empty")
    public void theDatePickerIsEmpty() {
        assertTrue(page().isDatePickerEmpty());
    }

    @And("the slider is centred")
    public void theSliderIsCentred() {
        assertTrue(page().sliderValue().contains("5"));
    }

    @And("tbe button is a Submit button")
    public void tbeButtonIsSubmit() {
        assertEquals("submit", page().submitButtonType());
    }

    @And("there is a link to the index page")
    public void theLinkIsToTheIndexPage() {
        page().openIndexLink();
        assertEquals("https://www.selenium.dev/selenium/web/index.html", driver.getCurrentUrl());
    }

    @When("I submit the form with valid data")
    public void fill_form() {
        page().completeFormAndSubmit();
    }

    @Then("I see a confirmation message")
    public void verify_success_message() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertEquals(CONFIRMATION_MESSAGE, message.getText());
    }

    @After
    public void tearDownScenario() {
        sharedDriver.close();
        driver = null;
        submitFormPage = null;
        wait = null;
    }

    private SubmitFormPage page() {
        if (submitFormPage == null) {
            throw new IllegalStateException("Submit form page has not been initialised. Navigate to the page before interacting with it.");
        }
        return submitFormPage;
    }
}
