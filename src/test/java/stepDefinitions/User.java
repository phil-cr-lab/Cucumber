package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static stepDefinitions.Common_Steps.*;

public class User {

    //UI steps ***********************************************************************************************

    @Given("I access the Signup Page")
    public void i_access_the_signup_page() {
        driver.get("https://thinking-tester-contact-list.herokuapp.com/addUser");
    }


    @When("I enter a first name")
    public void i_enter_a_first_name() {
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(user.getFirstName());
    }

    @When("^I enter a firstName (.*)$")
    public void iEnterAFirstName(String firstName) {
        user.setFirstName(firstName);
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(user.getFirstName());
    }

    @And("I enter a last name")
    public void i_enter_a_last_name() {
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(user.getLastName());
    }

    @And("^I enter a lastName (.*)$")
    public void iEnterALastName(String lastName) {
        user.setLastName(lastName);
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(user.setLastName());
    }

    @And("I enter an email address")
    public void i_enter_an_email_address() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user.getEmail());
    }

    @And("^I enter an email (.*)$")
    public void iEnterAnEmail(String email) {
        email = email.replace("@", System.currentTimeMillis() + "@");
        user.setEmail(email);
        System.out.println(email);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user.getEmail());
    }

    @And("I enter a password")
    public void i_enter_a_password() {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
    }

    @And("^I enter a password (.*)$")
    public void iEnterAPassword(String password) {
        user.setPassword(password);
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

    @And("I enter an existing account email address")
    public void i_enter_an_existing_account_email_address() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user.getEmail());
    }

    @And("I enter its associated password")
    public void i_enter_its_associated_password() {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
    }

    //API steps ***********************************************************************************************

    @When("I send the request to log in")
    public void iSendTheRequestToLogIn() {
        response = request
                .header("Content-Type", "application/json")
                .body(data)
                .post("https://thinking-tester-contact-list.herokuapp.com/users/login");
    }

    @When("I send the request to get the user information")
    public void iSendTheRequestToGetTheUserInformation() {
        response = request
                .header("Authorization", "Bearer " + token)
                .get("https://thinking-tester-contact-list.herokuapp.com/users/me");
    }

    @When("I send the request to add a new user")
    public void iSendTheRequestToAddANewUser() {
        response = request
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(data)
                .post("https://thinking-tester-contact-list.herokuapp.com/users");
    }

    @Then("I receive the new user information")
    public void iReceiveTheNewUserInformation() {
        Assert.assertEquals(201, response.getStatusCode());
        TestDataConstructs.User.validateUserInformation(user, response);
    }

    @When("I send the request to delete the user")
    public void iSendTheRequestToDeleteTheUser() {
        response = request
                .header("Authorization", "Bearer " + token)
                .delete("https://thinking-tester-contact-list.herokuapp.com/users/me");
    }

    @Then("I receive an http 200 OK code")
    public void iReceiveAnHttpOKCode() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @And("I cannot login with this user anymore")
    public void iCannotLoginWithThisUserAnymore() {

    }
}
