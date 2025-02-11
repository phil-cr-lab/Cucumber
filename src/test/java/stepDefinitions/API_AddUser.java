package stepDefinitions;

import io.cucumber.java.en.When;
import io.restassured.http.Header;

import static stepDefinitions.Common_Steps.*;

public class API_AddUser {

    @When("I send the request to add a new user")
    public void iSendTheRequestToAddANewUser() {
        Header authorizationHeader = new Header("Authorization", "Bearer{{token}}");
        request
            .header(authorizationHeader)
            .body(data);
        response = request.post("https://thinking-tester-contact-list.herokuapp.com/users");
    }

}
