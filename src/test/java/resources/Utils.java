package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if (req==null) {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging,txt"));
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build(); //RequestSpecification is return type
		
		return req;
		} 
		return req;
	}

	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream FIS = new FileInputStream("D:\\eclipse-workspace\\NewWorkspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(FIS);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key) {
		
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
		
	}
	
	
}
