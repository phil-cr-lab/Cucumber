package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static stepDefinitions.Common_Steps.driver;
import static stepDefinitions.Common_Steps.user;

public class SignupLoginLogout_Steps {

    private static boolean isTestDataCleaned = false;

    @Before("@signup")
    public static void cleanUpTestData() {
        try {
            Path filePath = Path.of("temp_user_data.json");
            if (Files.exists(filePath) && !isTestDataCleaned) {
                Files.delete(filePath);
                isTestDataCleaned = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("I access the Signup Page")
    public void i_access_the_signup_page() {
        driver.get("https://thinking-tester-contact-list.herokuapp.com/addUser");
    }

    @When("I enter a first name")
    public void i_enter_a_first_name() {
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(user.getFirstName());
    }

    @And("I enter a last name")
    public void i_enter_a_last_name() {
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(user.getLastName());
    }

    @And("I enter an email address")
    public void i_enter_an_email_address() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user.getEmail());
    }

    @And("I enter a password")
    public void i_enter_a_password() {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
    }

    @And("I click the submit button")
    public void i_click_the_submit_button() {
        driver.findElement(By.xpath("//button[@id='submit']")).click();
    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        driver.findElement(By.xpath("//body/descendant::header/button[@id='logout']")).click();
    }

    @Then("I see the login page")
    public void iSeeTheLoginPage() {
        WebElement contactListAppTitle = driver.findElement(By.xpath("//body/h1"));
        Assert.assertEquals("Contact List App", contactListAppTitle.getText());
        WebElement loginLabel = driver.findElement(By.xpath("//body/div[@class='main-content']/p"));
        Assert.assertEquals("Log In:", loginLabel.getText());
    }

    @When("I enter the email address of the created user")
    public void i_enter_the_email_address_of_the_created_user() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user.getEmail());
    }

    @And("I enter the password of the created user")
    public void i_enter_the_password_of_the_created_user() {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        driver.findElement(By.xpath("//button[@id='submit']")).click();
    }
}
