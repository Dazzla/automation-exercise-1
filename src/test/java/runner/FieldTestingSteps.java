package runner;

import WebDriverHelpers.SharedDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.SubmitFormPage;
import WebDriverHelpers.WebDriverFactory;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class FieldTestingSteps {

    private final SharedDriver sharedDriver;

    public FieldTestingSteps(SharedDriver sharedDriver) {

        this.sharedDriver = sharedDriver;
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


    @Then("I see that the Text Input is empty")
    public void iSeeThatTheTextInputIsEmpty() {
        WebDriver driver = sharedDriver.getDriver();
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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
