package TestDataConstructs;

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
}

