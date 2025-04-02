package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SubmitFormPage {

    final String NAME = "Test User";
    final String PASSWORD = "complexpassword";
    public static final String EMPTY = "";
    public WebDriver driver;
    public WebElement textInput;
    public WebElement passwordInput;
    public WebElement textAreaInput;
    public WebElement dropdownSelectInput;
    public WebElement datalistInput;
    public WebElement checkedCheckboxInput;
    public WebElement defaultCheckboxInput;
    public WebElement radioInput;
    public WebElement colorPickerInput;
    public WebElement datePickerInput;
    public WebElement sliderInput;

    public SubmitFormPage(WebDriver driver){
       this.driver = driver;
       textInput = driver.findElement(By.id("my-text-id"));
       passwordInput = driver.findElement(By.name("my-password"));
       textAreaInput = driver.findElement(By.name("my-textarea"));
       dropdownSelectInput = driver.findElement(By.name("my-select"));
       datalistInput = driver.findElement(By.name("my-datalist"));
       checkedCheckboxInput = driver.findElement(By.id("my-check-1"));
       defaultCheckboxInput = driver.findElement(By.id("my-check-2"));
       radioInput = driver.findElement(By.name("my-radio"));
       colorPickerInput = driver.findElement(By.name("my-colors"));
       datePickerInput = driver.findElement(By.name("my-date"));
       sliderInput = driver.findElement(By.name("my-range"));
   }

    public void completeFormAndSubmit(){completeForm(); submitForm();}

    public void completeForm(){
        textInput.sendKeys(NAME);
        passwordInput.sendKeys(PASSWORD);
        textAreaInput.sendKeys("Sample Text");
        dropdownSelectInput.sendKeys("Two");
        datalistInput.sendKeys("San Francisco");
        checkedCheckboxInput.click();
        radioInput.click();
        colorPickerInput.sendKeys("red");
        datePickerInput.sendKeys("12/12/2020");
        sliderInput.sendKeys(Keys.ARROW_RIGHT);
}

    public void submitForm(){
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public void visit() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }
    
    public void verifyStateOfFields(WebElement element, String state){
        assertEquals("", textInput.getText());
    }

}
