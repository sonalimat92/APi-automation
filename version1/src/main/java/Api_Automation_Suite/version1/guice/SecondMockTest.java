package Api_Automation_Suite.version1.guice;

import org.testng.Assert;

import PayloadTest.PayloadData;
import io.restassured.path.json.JsonPath;

public class SecondMockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		 JsonPath jsonPath= new JsonPath(PayloadData.mockRespponse());
		 String purchaseAmountString=jsonPath.getString("dashboard.purchaseAmount");
		 System.out.println(purchaseAmountString);
		 
		 int count=jsonPath.getInt("courses.size()");
		 System.out.println(count);
		 
		 String titleString=jsonPath.getString("courses[0].title");
		 System.out.println(titleString);
		 
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
			Assert.assertEquals(amountactualprice, sum);
			
		 }

	}

}
