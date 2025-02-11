package stepDefinitions;

import TestDataConstructs.User;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static stepDefinitions.Common_Steps.driver;
import static stepDefinitions.Common_Steps.user;

public class LoginLogout_Steps {

    @Before("@login")
    public void readTestData() {
        user = User.readTestDataFromFile();
    }

    @And("I enter an existing account email address")
    public void i_enter_an_existing_account_email_address() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user.getEmail());
    }

    @And("I enter its associated password")
    public void i_enter_its_associated_password() {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
    }
}
