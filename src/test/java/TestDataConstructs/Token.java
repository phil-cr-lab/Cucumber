package TestDataConstructs;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Token {
    public static void writeTokenToFile(String token) {
        //If tests had to store sensitive that way, we would need to encrypt
        //TODO: Encrypt sensitive data
        try (FileWriter writer = new FileWriter("token.txt")) {
            writer.write(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readTokenFromFile() {
        //If tests had to store sensitive that way, we would need to encrypt
        //TODO: Decrypt sensitive data

        try {
            return FileUtils.readFileToString(Path.of("token.txt").toFile(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void validateTokenFormat(String token) {
        String regex_JWT = "^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$";
        Assert.assertTrue(Pattern.matches(regex_JWT, token));
    }
}
