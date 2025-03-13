package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.FirstAPI_POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FirstAPIEndpoints2 {
	
	static ResourceBundle getUrl()
	{
		ResourceBundle rb = ResourceBundle.getBundle("routes");
		return rb;
	}
	
	public static Response createUser(FirstAPI_POJO payload)
	{
		String post_url_from_prop_file = getUrl().getString("post_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			//.post(Routes.post_url);
			.post(post_url_from_prop_file);
		
		return response;
	}
	
	public static Response getUser(String userName)
	{
		Response response = given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
		
		return response;		
	}
	
	public static Response updateuser(String userName, FirstAPI_POJO payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
				.pathParam("username", userName)
			.when()
				.get(Routes.delete_url);
			
		return response;
	}
	
	
	
	
	
	
	
	

}
