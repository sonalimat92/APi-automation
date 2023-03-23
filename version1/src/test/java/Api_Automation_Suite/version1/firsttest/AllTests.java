package Api_Automation_Suite.version1.firsttest;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.schemas.office.x2006.encryption.STHashSize;

import PayloadTest.PayloadData;
import cucumber.api.junit.Cucumber;

import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;


public class AllTests extends BaseTest{
	
	//private final Logger log = LoggerHelper.getLogger(AppTest2.class);	
	
	
	@Test
	public void newtest() {
		test = extent.createTest(" Test", "Validation of purchase amount equals total amount");
		
		 JsonPath jsonPath= new JsonPath(PayloadData.mockRespponse());
		 String purchaseAmountString=jsonPath.getString("dashboard.purchaseAmount");
		 System.out.println(purchaseAmountString);
		 test.log(Status.PASS, "Purchase amount: "+purchaseAmountString);
		 int count=jsonPath.getInt("courses.size()");
		 System.out.println(count);
		 test.log(Status.PASS, "Number of items: "+count);
		 String titleString=jsonPath.getString("courses[0].title");
		 System.out.println(titleString);
		 test.log(Status.PASS, "Title string is "+purchaseAmountString);
		 for(int i=0;i<count;i++) {
			 String courseString=jsonPath.getString("courses["+i+"].title");
			 Integer priceInteger=jsonPath.getInt("courses["+i+"].price");
			 //courses[].title  courses[ 0 ].title
			 System.out.println(courseString+" /  "+priceInteger);
			System.out.println("courseString");
		 }
			
		for(int j=0;j<count;j++) {
			String titleString2=jsonPath.getString("courses["+j+"].title");
			if(titleString2.equalsIgnoreCase("Cypress")) {
				int copies=jsonPath.getInt("courses["+j+"].copies");
				System.out.println(copies);
				break;
				
			}
			
			int sum=0;
			for(int k=0;k<count;k++) {
				int price=jsonPath.getInt("courses["+k+"].price");
				int copies=jsonPath.getInt("courses["+k+"].copies");
				int amount=price*copies;
				sum=sum+amount;
				
			}
			System.out.println(sum);
			int amountactualprice=jsonPath.getInt("dashboard.purchaseAmount");
			 test.log(Status.PASS, "Total amount :"+amountactualprice);
			Assert.assertEquals(amountactualprice, sum);
			 test.log(Status.PASS, "Purchase amount is equal to the Total amount ");
		 }

	}
	
	
}
