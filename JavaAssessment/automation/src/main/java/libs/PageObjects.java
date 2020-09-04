package libs;

import org.openqa.selenium.By;

//Page objects class
public class PageObjects {
	
	By lnkCareers = By.linkText("CAREERS");
	By lnkJob = By.linkText("Interns - BSC Computer Science, National Diploma: IT Development Graduates");
	By lnkApply = By.linkText("Apply Online");
	By txtName = By.id("applicant_name");
	By txtEmail = By.id("email");
	By txtPhone = By.id("phone");
	By lblMessage = By.cssSelector(".wpjb-errors > li");
	By btnSumit = By.id("wpjb_submit");
}
