package TestDataConstructs;

import org.junit.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class Token {
    String token;

    public Token(String token)
    {
        this.token = token;
    }

    public static void writeTokenToFile(String token) {
        //If tests had to store sensitive that way, we would need to encrypt
        //TODO: Encrypt sensitive data
        try (FileWriter writer = new FileWriter("token.txt")) {
            writer.write(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validateTokenFormat(String token) {
        String regex_JWT = "^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$";
        Assert.assertTrue(Pattern.matches(regex_JWT, token));
    }
}
