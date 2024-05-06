package stepDefinations;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJOClasses.AddPlace;
import POJOClasses.Location;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utillities;

public class StepDefination {

	private AddPlace place;
	private JsonPath respJSPath;
	private Response response;
	static String place_id;
	private String deletePlacePayload;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) {
		// Write code here that turns the phrase above into concrete actions

		place = TestDataBuild.getPlace(name, address, language);
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resourceName, String httpMethod) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		APIresources apiResources = APIresources.valueOf(resourceName);
		String apiResourceName = apiResources.getResource();

		if (httpMethod.equalsIgnoreCase("POST")) {
			response = Utillities.callAddPlaceAPI(place, apiResourceName);
			place_id = Utillities.getValueFromResponseBody(response, "place_id");
		}
			
		else if(httpMethod.equalsIgnoreCase("DELETE")) {
			response = Utillities.callDeletePlaceAPI(deletePlacePayload, apiResourceName);
		}
			

	}

	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer expectedStatusCode) {
		// Write code here that turns the phrase above into concrete actions
		Integer actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, expectedStatusCode);
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		respJSPath = new JsonPath(response.asString());
		Assert.assertEquals(respJSPath.getString(string), string2);
	}

	@And("verify place_id created maps to {string} calling {string} with {string} http request")
	public void verify_place_id_created_maps_to_calling_with_http_request_and_response_status_code(String name,
			String resourceName, String httpMethod) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		APIresources apiResources = APIresources.valueOf(resourceName);
		String apiResourceName = apiResources.getResource();
		
		place_id = Utillities.getValueFromResponseBody(response, "place_id");
		respJSPath = new JsonPath(response.asString());
		
		response = Utillities.callGetPlaceAPI(place_id, apiResourceName);
		String nameFromResponse = Utillities.getValueFromResponseBody(response, "name");
		Assert.assertEquals(name, nameFromResponse);
	}

	@Given("deletePlace Payload")
	public void delete_place_payload() {
		// Write code here that turns the phrase above into concrete actions
		
		deletePlacePayload = TestDataBuild.deletePlacePayload(place_id);
	}
}
