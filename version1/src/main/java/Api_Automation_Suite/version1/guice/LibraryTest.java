package Api_Automation_Suite.version1.guice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;

import PayloadTest.PayloadData;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class LibraryTest {
	
	@Test
	public static void librarytests() {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		String respString=given().header("Content-Type","application/json")
				.body(PayloadData.librarypayload("81910","hjshshh")).when().post("Library/Addbook.php").then().log().all()
				.extract().asString();
	
	}
	
	

}
