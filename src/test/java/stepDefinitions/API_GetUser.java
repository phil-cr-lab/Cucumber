package stepDefinitions;

import io.cucumber.java.en.When;

import static stepDefinitions.Common_Steps.*;

public class API_GetUser {

    @When("I send the request to get the user information")
    public void iSendTheRequestToGetTheUserInformation() {
        response = request
                .header("Authorization", "Bearer " + token)
                .get("https://thinking-tester-contact-list.herokuapp.com/users/me");
    }

}
