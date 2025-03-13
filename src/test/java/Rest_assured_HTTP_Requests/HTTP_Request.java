package Rest_assured_HTTP_Requests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HTTP_Request {
	
	int id;
	
	//@Test(priority=1)
	void getUsers()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	//@Test(priority=2)
	void postUser()
	{
		HashMap hm = new HashMap();
		hm.put("name","Raj");
		hm.put("role","Painter");		
				
		id = given()
		.contentType("application/json")
		.body(hm)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
//		.then()
//			.statusCode(201)
//			.log().all();		
	}
	
	//@Test(priority=3, dependsOnMethods={"postUser"})
	void putUser()
	{
		HashMap hm1 = new HashMap();
		//hm.put("name", "Ravi")
		hm1.put("role", "Driver");
		
		given()
			.contentType("application/json")
			.body(hm1)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test(priority=4)
	void deleteUser()
	{
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
	
	//@Test(priority=5)
	void post_Using_POJO()
	{
		POJO_Class pd = new POJO_Class();
		
		pd.setName("Ramesh");
		pd.setRole("Engineer");
		
		given()
			.contentType("application/json")
			.body(pd)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();
	}
	
	//@Test
	void post_Using_External_Json_File() throws FileNotFoundException
	{
		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jd = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(jd.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();
	}
	
	// Response - headers and cookies
	@Test
	void getUsers1()
	{
		Response res = given()
						.headers("Authorization", "beaer hqvdh7te72637263")
						.when()
							.get("https://www.google.com/");
		
		res.getCookie("AEC"); //returns mentioned cookie value
		res.getHeader("content-Type"); //returns mentioned header value
		
		Headers all_headerValue = res.getHeaders(); //return all headers both key-value
		Map<String, String> all_cookieValue = res.getCookies();//return all cookies both key-value
		
		all_cookieValue.keySet(); //return all cookies key
		all_cookieValue.values(); 
	}
	
	// API chaining 
	int id1;
	@Test
	void getUser3(ITestContext varValue)
	{
		id1 = given()
		.contentType("application/json")
		.headers("Authozation", "bearear yuqegdyu676718")
		.when()
			.get("https://reqres.in/api/users?page=2")
			.jsonPath().getInt("id");
		
		varValue.setAttribute("user_id", id1);
	}
	
	//assume this method is present in different class
	@Test
	void getUser4(ITestContext varValue)
	{
		int id2 = (int) varValue.getAttribute("user_id");
		given()
		.contentType("application/json")
		.headers("Authozation", "bearear yuqegdyu676718")
		.when()
			.get("https://reqres.in/api/users?page="+id2)
			.jsonPath().getInt("id");		
	}
	
	

}
