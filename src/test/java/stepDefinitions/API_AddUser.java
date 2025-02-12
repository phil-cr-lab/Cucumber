package stepDefinitions;

import TestDataConstructs.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static stepDefinitions.Common_Steps.*;

public class API_AddUser {

    @When("I send the request to add a new user")
    public void iSendTheRequestToAddANewUser() {
        System.out.println(token);
        System.out.println(data);
        response = request
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(data)
                .post("https://thinking-tester-contact-list.herokuapp.com/users");
    }

    @Then("I receive the new user information")
    public void iReceiveTheNewUserInformation() {
        System.out.println("I receive new user information response: " + response.asPrettyString());
        Assert.assertEquals(201, response.getStatusCode());
        User.validateUserInformation(user, response.asString());
    }
}
