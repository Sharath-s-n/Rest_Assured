package api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.FirstAPIEndpoints;
import api.payload.FirstAPI_POJO;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class FirstAPI_TestCases {
	
	Faker faker;
	FirstAPI_POJO pojoClass;
	public Logger logger;
	
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
		
		//logger= LogManager.getLogger(this.getClass());
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	void testPostUser()
	{
		logger.info("********Post User******");
		Response response = FirstAPIEndpoints.createUser(pojoClass);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********Post User done******");
	}
	
	//@Test(priority=2, dataProvider = "data", dataProviderClass = DataProvider.class)
	void testGetUser()
	{
		logger.info("********get User******");
		Response response = FirstAPIEndpoints.getUser(this.pojoClass.getUserName());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********Get User done******");
	}
	
	
	
	
	
	
	
	

}
