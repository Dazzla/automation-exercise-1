package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SubmitFormPage {

    public static final String RED = "red";
    public static final String SAN_FRANCISCO = "San Francisco";
    public static final String TWO = "Two";
    public static final String SAMPLE_TEXT = "Sample Text";
    public static final String DATE = "12/12/2020";
    public static final String EMPTY = "";

    private static final String DEFAULT_NAME = "Test User";
    private static final String DEFAULT_PASSWORD = "complexpassword";

    private final WebDriver driver;

    private final By textInput = By.id("my-text-id");
    private final By passwordInput = By.name("my-password");
    private final By textAreaInput = By.name("my-textarea");
    private final By dropdownSelect = By.name("my-select");
    private final By datalistInput = By.name("my-datalist");
    private final By checkedCheckboxInput = By.id("my-check-1");
    private final By defaultCheckboxInput = By.id("my-check-2");
    private final By checkedRadioInput = By.id("my-radio-1");
    private final By uncheckedRadioInput = By.id("my-radio-2");
    private final By colorPickerInput = By.name("my-colors");
    private final By datePickerInput = By.name("my-date");
    private final By sliderInput = By.name("my-range");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By linkToIndexPage = By.cssSelector("a[href='./index.html']");

    public SubmitFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void completeFormAndSubmit() {
        completeForm();
        submitForm();
    }

    public void completeForm() {
        type(textInput, DEFAULT_NAME);
        type(passwordInput, DEFAULT_PASSWORD);
        type(textAreaInput, SAMPLE_TEXT);
        selectDropdownOption(TWO);
        type(datalistInput, SAN_FRANCISCO);
        ensureSelected(checkedCheckboxInput);
        ensureSelected(uncheckedRadioInput);
        type(colorPickerInput, RED);
        type(datePickerInput, DATE);
        press(sliderInput, Keys.ARROW_RIGHT);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public boolean isTextInputEmpty() {
        return value(textInput).isBlank();
    }

    public boolean isPasswordInputEmpty() {
        return value(passwordInput).isBlank();
    }

    public boolean isTextAreaEmpty() {
        return value(textAreaInput).isBlank();
    }

    public String dropdownContents() {
        return driver.findElement(dropdownSelect).getText();
    }

    public boolean isDatalistEmpty() {
        return value(datalistInput).isBlank();
    }

    public boolean isCheckedCheckboxSelected() {
        return driver.findElement(checkedCheckboxInput).isSelected();
    }

    public boolean isDefaultCheckboxSelected() {
        return driver.findElement(defaultCheckboxInput).isSelected();
    }

    public boolean isCheckedRadioSelected() {
        return driver.findElement(checkedRadioInput).isSelected();
    }

    public boolean isDefaultRadioSelected() {
        return driver.findElement(uncheckedRadioInput).isSelected();
    }

    public String colorPickerBackground() {
        return driver.findElement(colorPickerInput).getCssValue("background-color");
    }

    public boolean isDatePickerEmpty() {
        return value(datePickerInput).isBlank();
    }

    public String sliderValue() {
        return driver.findElement(sliderInput).getAttribute("value");
    }

    public String submitButtonType() {
        return driver.findElement(submitButton).getAttribute("type");
    }

    public void openIndexLink() {
        driver.findElement(linkToIndexPage).click();
    }

    private void selectDropdownOption(String visibleText) {
        new Select(driver.findElement(dropdownSelect)).selectByVisibleText(visibleText);
    }

    private void ensureSelected(By locator) {
        WebElement element = driver.findElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    private void press(By locator, Keys key) {
        driver.findElement(locator).sendKeys(key);
    }

    private void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        try {
            element.clear();
        } catch (InvalidElementStateException ignored) {
            // Some inputs (e.g. color pickers) do not support clear()
        }
        element.sendKeys(text);
    }

    private String value(By locator) {
        return driver.findElement(locator).getAttribute("value");
    }
}
