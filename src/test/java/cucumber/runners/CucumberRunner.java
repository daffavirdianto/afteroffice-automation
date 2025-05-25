package cucumber.runners;

import org.testng.annotations.AfterSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources",
        glue = "cucumber.definitions",
        plugin = {"pretty", "json:target/cucumber.json"},
        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @AfterSuite
    public void after_suite() {
        System.out.println("Cucumber runner start...");
    }
}