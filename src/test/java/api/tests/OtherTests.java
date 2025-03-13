package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.FirstAPIEndpoints;
import api.payload.FirstAPI_POJO;
import io.restassured.response.Response;

public class OtherTests {
	Faker faker;
	FirstAPI_POJO pojoClass;
	
	@BeforeClass
	void setUpData()
	{
		faker = new Faker();
		pojoClass = new FirstAPI_POJO();
		
		pojoClass.setId(faker.idNumber().hashCode());
		pojoClass.setUserName(faker.name().username());
		pojoClass.setFirstName(faker.name().firstName());
		pojoClass.setLastName(faker.name().lastName());
		pojoClass.setEmai(faker.internet().safeEmailAddress());
		pojoClass.setPassword(faker.internet().password(5, 10));
		pojoClass.setPhone(faker.phoneNumber().cellPhone());		
	}
	
	@Test(priority=1)
	void testPostUser()
	{
		Response response = FirstAPIEndpoints.createUser(pojoClass);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
