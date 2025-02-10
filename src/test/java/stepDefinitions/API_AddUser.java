package stepDefinitions;

import TestDataConstructs.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class API_AddUser {
    private static User user;
    private static String data;
    private static RequestSpecification request;
    private static Response response;

    @Given("The user to add is new")
    public void theUserToAddIsNew() {
        user = new User();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        data = gson.toJson(user);
        request = given();
    }

    @When("I send the request to add a new user")
    public void iSendTheRequestToAddANewUser() {
        Header authorizationHeader = new Header("Authorization", "Bearer{{token}}");
        request
            .header(authorizationHeader)
            .body(data);
        response = request.post("https://thinking-tester-contact-list.herokuapp.com/users");
    }

    @Then("The new user is added")
    public void theNewUserIsAdded() {
        System.out.println("Response: " + response.asString());
        Assert.assertEquals(200, response.getStatusCode());
    }
}
