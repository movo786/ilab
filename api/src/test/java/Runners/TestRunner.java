package Runners;

import org.testng.annotations.Test;
import cucumber.api.testng.*;
import cucumber.api.CucumberOptions;
import org.testng.annotations.*;
import com.vimalselvam.cucumber.listener.*;
import com.vimalselvam.cucumber.listener.Reporter;

@CucumberOptions(features = "src/test/resources", glue = { "api" }, plugin = {
		"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:reports/cucumber-reports/report.html" })

public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
	}
}
