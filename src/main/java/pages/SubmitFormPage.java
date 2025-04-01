package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class SubmitFormPage {

    WebDriver driver;

    public SubmitFormPage(WebDriver driver){
       this.driver = driver;
   }

    public void completeFormAndSubmit(String name, String email){completeForm(name, email); submitForm();}

    public void completeForm(String name, String email){
        driver.findElement(By.id("my-text-id")).sendKeys("Test User");
        driver.findElement(By.name("my-password")).sendKeys("password123");
        driver.findElement(By.name("my-textarea")).sendKeys("Testing Selenium and Cucumber integration.");
        driver.findElement(By.cssSelector("select[name='my-select'] option[value='1']")).click();
}

    public void submitForm(){
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

}
