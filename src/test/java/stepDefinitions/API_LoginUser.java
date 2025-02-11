package stepDefinitions;

import TestDataConstructs.Token;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static stepDefinitions.Common_Steps.*;

public class API_LoginUser {
    @When("I send the request to log in")
    public void iSendTheRequestToLogIn() {
        request
                .header("Content-Type", "application/json")
                .body(data);
        response = request.post("https://thinking-tester-contact-list.herokuapp.com/users/login");
    }

    @Then("I receive the user information")
    public void iReceiveTheUserInformation() {
        System.out.println("Response: " + response.asPrettyString());
        Assert.assertEquals(200, response.getStatusCode());
        JsonObject jsonObjectResponse = new Gson().fromJson(response.asString(), JsonObject.class);

        JsonElement jsonElement = JsonParser.parseString(response.asString());

    }

    @And("I receive a token")
    public void iReceiveAToken() {
        JsonObject jsonObject = new Gson().fromJson(response.asString(), JsonObject.class);
        String token = jsonObject.get("token").getAsString();
        Token.validateTokenFormat(token);
        Token.writeTokenToFile(token);
    }
}
