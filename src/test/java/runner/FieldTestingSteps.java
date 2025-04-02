package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.SubmitFormPage;
import WebDriver.WebDriverFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;


public class FieldTestingSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    SubmitFormPage submitFormPage;


    @After
    public void tearDown() {
        driver.quit();
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }


    @Then("I see that the Text Input is empty")
    public void iSeeThatTheTextInputIsEmpty() {
        submitFormPage = new SubmitFormPage(driver);
        assertEquals("", driver.findElement(By.id("my-text-id")).getText());
    }

    @And("the password field is empty")
    public void thePasswordFieldIsEmpty() {
        fail("pending");
    }

    @And("the Textarea field is blank")
    public void theTextareaFieldIsBlank() {
        fail("pending");

    }

    @And("the Dropdown \\(select) is {string}")
    public void theDropdownSelectIsOpenThisSelectMenu() {
        fail("pending");

    }

    @And("the Dropdown \\(datalist) is blank")
    public void theDropdownDatalistIsBlank() {
        fail("pending");

    }

    @And("the checked checkbox is checked")
    public void theCheckedCheckboxIsChecked() {
        fail("pending");

    }

    @And("the default checkbox is unchecked")
    public void theDefaultCheckboxIsUnchecked() {
        fail("pending");

    }

    @And("the checked radio button is selected")
    public void theCheckedRadioButtonIsSelected() {
        fail("pending");

    }

    @And("the default radio button is unselected")
    public void theDefaultRadioButtonIsUnselected() {
        fail("pending");

    }

    @And("the color picker is purple")
    public void theColorPickerIsPurple() {
        fail("pending");

    }

    @And("the date picker is empty")
    public void theDatePickerIsEmpty() {
        fail("pending");

    }

    @And("the slider is centred")
    public void theSliderIsCentred() {
        fail("pending");

    }

    @And("tbe button is submit")
    public void tbeButtonIsSubmit() {
        fail("pending");

    }

    @And("the link is to the index page")
    public void theLinkIsToTheIndexPage() {
        fail("pending");
    }
}
