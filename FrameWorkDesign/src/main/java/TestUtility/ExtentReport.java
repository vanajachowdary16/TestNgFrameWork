package TestUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	static String PROJECT_PATH = System.getProperty("user.dir");
	public static String extentConfigPath = PROJECT_PATH+"\\results\\extentconfig.html";

	ExtentTest test;
	static ExtentReports extent;
	
	public static ExtentReports getReportObject() {
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(extentConfigPath);
		reporter.config().setReportName("Test_Results_Report");
		reporter.config().setDocumentTitle("Test_Automation_Results");
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "vanaja");
		extent.createTest(extentConfigPath);
		return extent;
		
	}
	
}
