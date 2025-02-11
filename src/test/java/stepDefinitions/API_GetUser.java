package stepDefinitions;

import io.cucumber.java.en.When;
import io.restassured.http.Header;

import static stepDefinitions.Common_Steps.*;

public class API_GetUser {

    @When("I send the request to get the user information")
    public void iSendTheRequestToGetTheUserInformation() {
        Header authorizationHeader = new Header("Authorization", "Bearer{{token}}");
        request
                .header(authorizationHeader)
                .body(data);
        response = request.post("https://thinking-tester-contact-list.herokuapp.com/users");
    }

}
