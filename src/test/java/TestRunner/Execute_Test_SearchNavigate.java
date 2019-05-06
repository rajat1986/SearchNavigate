package TestRunner;
//import stepimplementation.*;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:Features",
		glue = "stepimplementation",
		plugin = {"pretty", "html:target/cucumber-html-report"},
		format = {"json:target/cucumber.json", "html:target/Destination/cucumber-pretty"},
		tags = {}
		)

public class Execute_Test_SearchNavigate {

}
