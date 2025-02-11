package stepDefinitions;

import io.cucumber.java.en.When;

import static stepDefinitions.Common_Steps.*;

public class API_LoginUser {
    @When("I send the request to log in")
    public void iSendTheRequestToLogIn() {
        request
                .header("Content-Type", "application/json")
                .body(data);
        response = request.post("https://thinking-tester-contact-list.herokuapp.com/users/login");
    }

}
