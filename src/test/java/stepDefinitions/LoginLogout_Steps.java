package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import TestDataConstructs.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginLogout_Steps {

    private static User user = new User();
    private static WebDriver driver = SignupLoginLogout_Steps.driver;

    @Before("@login")
    public void readTestData() {
        user = readTestDataFromFile();
    }

    @And("I enter an existing account email address")
    public void i_enter_an_existing_account_email_address() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user.getEmail());
    }

    @And("I enter its associated password")
    public void i_enter_its_associated_password() {
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(user.getPassword());
    }

    private static User readTestDataFromFile() {
        Gson gson = new Gson();
        User user = null;
        try (FileReader reader = new FileReader(Path.of("temp_user_data.json").toFile())) {
            user = gson.fromJson(reader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
