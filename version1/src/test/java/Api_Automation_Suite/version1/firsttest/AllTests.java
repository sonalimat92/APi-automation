package Api_Automation_Suite.version1.firsttest;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import com.microsoft.schemas.office.x2006.encryption.STHashSize;

import cucumber.api.junit.Cucumber;
import static io.restassured.RestAssured.*;


public class AllTests {
	@Test
	public void newtest() {
		given().when().get("http://ergast.com/api/f1/2017/circuits.json")
		.then()
		.assertThat()
		.body("MRData.CircuitTable.Circuits.circuitId",hasSize(20))
		.and()
		.statusCode(400)
		.and()
		.header("Content-Length", equalTo("4552"));
	}

}