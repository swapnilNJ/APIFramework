package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;


public class StepDefination extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	public static String place_id;
	
	
	@Given("Add Place Payload with {string}, {string}, {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions

		
		//RequestSpecBuilder
		
		//ResponseSpecBuilder
		 
		 res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
		 
		 
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    
		 APIresources resourcesAPI = APIresources.valueOf(resource);
		 System.out.println(resourcesAPI.getResources());
		
		// Write code here that turns the phrase above into concrete actions
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resourcesAPI.getResources());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourcesAPI.getResources());
		
				//.then().spec(resspec).extract().response();
	}
	@Then("the API calls is success with status code {int}")
	public void the_api_calls_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_resposne_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(response, keyValue),Expectedvalue);
	
	
	}
	
	@Then ("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	// Write code here that turns the phrase above into concrete actions
	
		String place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName,expectedName);
	
	}
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}


	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
	}


}
	    
	
	
	



	

