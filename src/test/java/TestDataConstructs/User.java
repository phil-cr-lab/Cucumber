package TestDataConstructs;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;

    public User() {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.password = null;
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

