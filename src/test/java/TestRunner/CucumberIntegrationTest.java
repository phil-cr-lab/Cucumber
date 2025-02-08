package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        // Package name where step definitions are located
        glue = "stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)

public class CucumberIntegrationTest {
}
