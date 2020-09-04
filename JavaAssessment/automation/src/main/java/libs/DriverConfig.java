package libs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.ie.*;

//class to setup driver configs

public class DriverConfig {

	private WebDriver driver;

	public DriverConfig(String browser) {
		
		//Create driver based on parameter
		
		browser = browser.toLowerCase();

		switch (browser) {
		case "ie":
			
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.disableNativeEvents();
			options.enablePersistentHovering();
			driver = new InternetExplorerDriver(options);
			break;

		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;
		}
		
		//set timeouts
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	//returns current driver
	public WebDriver getDriver() {
		return driver;
	}

	//closes driver
	public void close() {
		driver.close();
	}

	//nigate to specified url
	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	//maximise browser
	public void maximise() {
		driver.manage().window().maximize();
	}
}
