import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/featureFiles",
        glue = {"stepDefinitions", "utils"},
        plugin = {"pretty",
                "html:target/HtmlReports.html",
                "json:target/JsonReports.json",
                "junit:target/JunitReports.xml"},
        monochrome = true,//display the console output
        tags = "@Regression"
)

public class testRunner {

}
