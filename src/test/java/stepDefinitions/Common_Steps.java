package stepDefinitions;

import TestDataConstructs.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static io.restassured.RestAssured.given;


public class Common_Steps {

    static WebDriver driver;
    static User user;
    static String data;
    static RequestSpecification request;
    static Response response;

    @BeforeAll
    public static void beforeAll() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        request = given();
    }

    @AfterAll
    public static void AfterAll() {
        driver.quit();
    }

    @Given("The user to add is new")
    public void theUserToAddIsNew() {
        user = new User();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        data = gson.toJson(user);
    }

    @Given("The user already exists")
    public void theUserAlreadyExists() {
        user = User.readTestDataFromFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        data = gson.toJson(user);
    }

    @Given("I access the user Login Page")
    public void i_access_the_login_page() {
        driver.get("https://thinking-tester-contact-list.herokuapp.com");
    }

    @Then("I see the contact list page")
    public void i_see_the_contact_list_page() {
        WebElement contactListTitle = driver.findElement(By.xpath("//body/descendant::header/h1"));
        Assert.assertEquals("Contact List", contactListTitle.getText());
        User.writeUserDataToFile(user);
    }
}
