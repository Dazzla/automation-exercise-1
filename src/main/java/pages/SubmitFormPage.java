package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SubmitFormPage {

    public static final String RED = "red";
    public static final String SAN_FRANCISCO = "San Francisco";
    public static final String TWO = "Two";
    public static final String SAMPLE_TEXT = "Sample Text";
    public static final String DATE = "12/12/2020";
    final String NAME = "Test User";
    final String PASSWORD = "complexpassword";
    public static final String EMPTY = "";
    public final WebDriver driver;
    public final WebElement textInput;
    public final WebElement passwordInput;
    public final WebElement textAreaInput;
    public final WebElement dropdownSelectInput;
    public final WebElement datalistInput;
    public final WebElement checkedCheckboxInput;
    public final WebElement defaultCheckboxInput;
    public final WebElement checkedRadioInput;
    public final WebElement uncheckedRadioInput;
    public final WebElement colorPickerInput;
    public final WebElement datePickerInput;
    public final WebElement sliderInput;
    public final WebElement submitButton;
    public final WebElement linkToIndexPage;

    public SubmitFormPage(WebDriver driver){
       this.driver = driver;
       textInput = driver.findElement(By.id("my-text-id"));
       passwordInput = driver.findElement(By.name("my-password"));
       textAreaInput = driver.findElement(By.name("my-textarea"));
       dropdownSelectInput = driver.findElement(By.name("my-select"));
       datalistInput = driver.findElement(By.name("my-datalist"));
       checkedCheckboxInput = driver.findElement(By.id("my-check-1"));
       defaultCheckboxInput = driver.findElement(By.id("my-check-2"));
       checkedRadioInput = driver.findElement(By.id("my-radio-1"));
       uncheckedRadioInput = driver.findElement(By.id("my-radio-2"));
       colorPickerInput = driver.findElement(By.name("my-colors"));
       datePickerInput = driver.findElement(By.name("my-date"));
       sliderInput = driver.findElement(By.name("my-range"));
       submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
       linkToIndexPage = driver.findElement(By.cssSelector("a[href='./index.html']"));
   }

    public void completeFormAndSubmit(){completeForm(); submitForm();}

    public void completeForm(){
        textInput.sendKeys(NAME);
        passwordInput.sendKeys(PASSWORD);
        textAreaInput.sendKeys(SAMPLE_TEXT);
        dropdownSelectInput.sendKeys(TWO);
        datalistInput.sendKeys(SAN_FRANCISCO);
        checkedCheckboxInput.click();
        uncheckedRadioInput.click();
        colorPickerInput.sendKeys(RED);
        datePickerInput.sendKeys(DATE);
        sliderInput.sendKeys(Keys.ARROW_RIGHT);
}

    public void submitForm(){
        driver.findElement(By.cssSelector("button[type='submit']")).click();

    }

}
