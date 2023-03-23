package Api_Automation_Suite.version1.firsttest;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports  extent;
	ExtentTest test;
	@BeforeSuite
	public static void startTest()
	{
		try {
            //Generating reports in test-output folder
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/newTest.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);

			htmlReporter.config().setDocumentTitle("Test Runs");
			htmlReporter.config().setReportName("Argoid Test Report");
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
			
			
		}     
		
	}
	
	@AfterSuite
	public void flushReport() {
		extent.flush();
	}
}
