package runners;

import org.testng.annotations.AfterSuite;

import helper.GenerateReport;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources",
        glue = "selenium.definitions",
        plugin = {"pretty", "json:target/cucumber.json"},
        monochrome = true
)
public class SeleniumRunner extends AbstractTestNGCucumberTests {

    @AfterSuite
    public void after_suite() {
        GenerateReport.generateReport();
        System.out.println("Cucumber runner start...");
    }
}
