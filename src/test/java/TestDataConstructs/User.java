package TestDataConstructs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;

    public User() {
        this.firstName = setFirstName();
        this.lastName = setLastName();
        this.email = setEmail();
        this.password = setPassword();
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String setFirstName(){ return this.firstName = "John"; }

    public String setLastName(){ return this.lastName = "Doe"; }

    public String setEmail(){ return this.email = "JohnDoe" + System.currentTimeMillis() + "@test.com"; }

    public String setPassword() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date now = Calendar.getInstance().getTime();
        return this.password = sdf.format(now); }

    public String setFirstName(String firstName) {
        return this.firstName = firstName;
    }

    public String setLastName(String lastName) {
        return this.lastName = lastName;
    }

    public String setEmail(String email) {
        return this.email = email;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public static void writeUserDataToFile(User user) {
        //If tests had to store sensitive that way, we would need to encrypt
        //TODO: Encrypt sensitive data
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("temp_user_data.json")) {
            gson.toJson(user, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User readTestDataFromFile() {
        //If tests had to retrieve encrypted sensitive data, we would need to decrypt
        //TODO: Decrypt sensitive data
        Gson gson = new Gson();
        User user = null;
        try (FileReader reader = new FileReader(Path.of("temp_user_data.json").toFile())) {
            user = gson.fromJson(reader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void validateUserInformation(User user, String response) {
        JsonElement JE_response = JsonParser.parseString(response).getAsJsonObject();


        Assert.assertEquals(user.getFirstName(), JO_firstName.getAsString());
        Assert.assertEquals(user.getLastName(), JO_lastName.getAsString());
        Assert.assertEquals(user.getEmail(), JO_email.getAsString());
    }
}

