package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
//        tags = "",
        plugin = {"json:target/jsonReports/cucumber-report.json"},
        monochrome = true)
public class RunnerTest {

}
