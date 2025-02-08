package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = "stepDefinitions",        // Package name where step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Report plugins
        monochrome = true                // Makes console output readable
)

public class CucumberIntegrationTest {
}
