package stepDefinitions;

import TestDataConstructs.User;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class SignupLoginLogout_Steps {

    static WebDriver driver;
    private static boolean isTestDataCleaned = false;
    private static User user = new User();

    @Before("@signup")
    public static void cleanUpTestData() {
        try {
            Path filePath = Path.of("C:\\Projects\\Cucumber\\temp_user_data.json");
            if (Files.exists(filePath) && !isTestDataCleaned) {
                Files.delete(filePath);
                isTestDataCleaned = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\Cucumber\\src\\main\\java\\drivers\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @AfterAll
    public static void AfterAll() {
        driver.quit();
    }

    @Given("I access the Signup Page")
    public void i_access_the_signup_page() {
        driver.get("https://thinking-tester-contact-list.herokuapp.com/addUser");
    }

    @Given("I access the Login Page")
    public void i_access_the_login_page() {
        driver.get("https://thinking-tester-contact-list.herokuapp.com");
    }

    @When("I enter a first name")
    public void i_enter_a_first_name() {
        String firstName = user.setFirstName("John");
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);
    }

    @And("I enter a last name")
    public void i_enter_a_last_name() {
        String lastName = user.setLastName("Doe");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);
    }

    @And("I enter an email address")
    public void i_enter_an_email_address() {
        String email = user.setEmail("johnDoe" + System.currentTimeMillis() + "@test.com");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
    }

    @And("I enter a password")
    public void i_enter_a_password() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date now = Calendar.getInstance().getTime();
        String password = user.setPassword(sdf.format(now));        
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    }

    @And("I click the submit button")
    public void i_click_the_submit_button() {
        driver.findElement(By.xpath("//button[@id='submit']")).click();
    }

    @Then("I see the contact list page")
    public void i_see_the_contact_list_page() {
        WebElement contactListTitle = driver.findElement(By.xpath("//body/descendant::header/h1"));
        Assert.assertEquals(contactListTitle.getText(), "Contact List");
        writeUserDataToFile();

    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        driver.findElement(By.xpath("//body/descendant::header/button[@id='logout']")).click();
    }

    @Then("I see the login page")
    public void iSeeTheLoginPage() {
        WebElement contactListAppTitle = driver.findElement(By.xpath("//body/h1"));
        Assert.assertEquals(contactListAppTitle.getText(), "Contact List App");
        WebElement loginLabel = driver.findElement(By.xpath("//body/div[@class='main-content']/p"));
        Assert.assertEquals(loginLabel.getText(), "Log In:");
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

    private void writeUserDataToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("C:\\Projects\\Cucumber\\temp_user_data.json")) {
            gson.toJson(user, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
