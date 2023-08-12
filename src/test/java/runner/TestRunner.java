package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
        //, features = "src/test/resources/features/Login.feature"
        , glue = {"stepdefinitions", "hooks"}
        //, dryRun = true //Non-implemented feature steps will be considered as an error
        , tags = "(not @ignore) and (not @wip) and (not @dev) and @loginNegativeTests"
        //, tags = "@smoke"
        //, tags = "@all" //run all
        //, publish = true
        , plugin = {"pretty",
                    "html:reports/cucumber-html-report.html",
                    //"json:target/cucumber-reports/cucumber-json-report.json"
                    }
        )
@Test
public class TestRunner extends AbstractTestNGCucumberTests {

}
