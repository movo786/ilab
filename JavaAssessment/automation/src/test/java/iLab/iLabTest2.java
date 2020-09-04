package iLab;

import static org.testng.Assert.assertEquals;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;

import libs.Keywords;
import libs.Utils;

public class iLabTest2 {
	private static Utils util = new Utils();
	private Keywords kw = null;
	private String url = "http://www.ilabquality.com/";
	
	@DataProvider
	public Object[][] testdata() {
			
		int rowCount = util.getRowCountFromDB();
		Object [][] data= new Object[rowCount][4];
		
		
		data = util.getDataFromDB();
		return data;
	}

	@Test(dataProvider = "testdata")
	public void testILabDB(String browser, String country, String name, String email) {
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
		kw.closeBrowser();
	}
}