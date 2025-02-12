package stepDefinitions;

import io.cucumber.java.en.When;

import static stepDefinitions.Common_Steps.*;

public class API_AddUser {

    @When("I send the request to add a new user")
    public void iSendTheRequestToAddANewUser() {
        System.out.println(data);
        response = request
                .header("Authorization", "Bearer " + token)
                .body(data)
                .post("https://thinking-tester-contact-list.herokuapp.com/users");
    }

}
