package iLab;

import static org.testng.Assert.assertEquals;
import org.testng.Reporter;
import org.testng.annotations.*;

import libs.Keywords;
import libs.Utils;

public class iLabTest {
	private static Utils util = new Utils();
	private Keywords kw = null;
	private String url = "http://www.ilabquality.com/";

	@Test
	@Parameters({ "browser", "country", "name", "email" })
	public void testILab(String browser, String country, String name, String email) {
		// initialise driver
		kw = new Keywords(browser);
		// test script
		kw.navigateToPage(url);
		kw.clickCareers();
		kw.selectCountry(country);
		kw.selectJob();
		kw.clickApply();
		kw.inputApplicantDetails(name, email, util.getPhoneNumber());
		kw.clickSendApplicaiton();
		Reporter.log("Verifying error message");
		assertEquals(kw.verifyErrorUpload(), "You need to upload at least one file.");
	}

	@AfterTest
	public void tearDown() {
		kw.closeBrowser();
	}
}
