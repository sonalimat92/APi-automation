package Api_Automation_Suite.version1.guice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
//import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;

import PayloadTest.PayloadData;

public class FirstApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//given--all input details
		//when-- Submit the api
		//then-- Validate the response
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
     String respString= given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
      .body(PayloadData.dataPayloString())
      .when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
      .extract().response().asString();
     System.out.println(respString);
     
     JsonPath jsonPath= new JsonPath(respString);
     String place_idString=jsonPath.getString("place_id");
     System.out.println(place_idString);
     String newadress="70 Summer walk, India";
     
     
     
     String respString3= given().log().all()
     		. queryParam("key", "qaclick123")
     	   .header("Content-Type","application/json")
      .body("{\n"
      		+ "\"place_id\":\""+place_idString+"\",\n"
      		+ "\"address\":\""+newadress+"\",\n"
      		+ "\"key\":\"qaclick123\"\n"
      		+ "}")
      .when().put("maps/api/place/update/json")
      .then().log().all().assertThat().statusCode(200)
      .extract().asString();
      
      JsonPath jsonPath3=new JsonPath(respString3);
      String msgString=jsonPath3.getString("msg");
      Assert.assertEquals("Address successfully updated", msgString);
    
     String respString2= given().queryParam("key", "qaclick123")
     .queryParam("place_id", place_idString)
     .when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
     .extract().response().asString();
       
     JsonPath jsonPath2 =new JsonPath(respString2);
     String language=jsonPath2.getString("language");
    System.out.println(language);
    
    String actualAddressString=jsonPath2.getString("address");
    Assert.assertEquals(newadress, actualAddressString);
    // Assert.assertEquals("French-IN",language );
     String latitudeString=jsonPath2.getString("location.latitude");
    System.out.println(latitudeString);
     
     
     
     
     
     
     
     
     
     
	
	}
	
}
