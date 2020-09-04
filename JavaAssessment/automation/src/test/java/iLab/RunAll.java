package iLab;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;

public class RunAll {
	public static void main(String[] args) {

		TestNG testSuite = new TestNG();
		List<String> suitefiles = new ArrayList<String>();
		String filePath;

		// run test with data in testng xml datafile and then from DB
		filePath = System.getProperty("user.dir") + "\\src\\test\\java\\iLab\\runTest.xml";
		suitefiles.add(filePath);
		testSuite.setTestSuites(suitefiles);
		testSuite.run();

	}
}
