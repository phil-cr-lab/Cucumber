package stepDefinitions;

import TestDataConstructs.Token;
import TestDataConstructs.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
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
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;

import static io.restassured.RestAssured.given;


public class Common_Steps {

    static WebDriver driver;
    static User user = new User("", "", "", "");
    static String data;
    static RequestSpecification request;
    static Response response;
    static String token;
    static String currentOS;

    @BeforeAll
    public static void beforeAll() {
        currentOS = System.getProperty("os.name");
    }

    @Before
    public static void before() throws InterruptedException {
        if (currentOS.contains("Win")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver(chromeOptions);
        } else if (currentOS.contains("Mac")) {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new SafariDriver(safariOptions);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        request = given();
    }

    @After
    public static void after() {
        driver.quit();
    }

    @Given("The user to add is new")
    public void theUserToAddIsNew() {
        user = new User();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        data = gson.toJson(user);
        //User.writeUserDataToFile(user);
    }

    @Given("The user already exists")
    public void theUserAlreadyExists() {
        //user = User.readTestDataFromFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        data = gson.toJson(user);
    }

    @And("I receive a token")
    public void iReceiveAToken() {
        JsonObject jsonObject = new Gson().fromJson(response.asString(), JsonObject.class);
        token = jsonObject.get("token").getAsString();
        Token.validateTokenFormat(token);
        //Token.writeTokenToFile(token);
    }

    @And("I have a token")
    public void iHaveAtoken() {
        Token.validateTokenFormat(token);
        //token = Token.readTokenFromFile();
    }

    @Given("I access the user Login Page")
    public void i_access_the_login_page() {
        driver.get("https://thinking-tester-contact-list.herokuapp.com");
    }

    @Then("I see the contact list page")
    public void i_see_the_contact_list_page() {
        WebElement contactListTitle = driver.findElement(By.xpath("//h1[text()=\"Contact List\"]"));
        Assert.assertEquals("Contact List", contactListTitle.getText());
        //User.writeUserDataToFile(user);
    }

    @Then("I receive the existing user information")
    public void iReceiveTheExistingUserInformation() {
        Assert.assertEquals(200, response.getStatusCode());
        User.validateUserInformation(user, response);
    }
}
