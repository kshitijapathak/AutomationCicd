package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber",
    glue = "stepDefinations",
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    tags = "@Regression"
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}

