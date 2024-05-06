package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void beforeDeleteScenario() throws IOException {
		StepDefination stepDef = new StepDefination();
		if(stepDef.place_id==null) {
			stepDef.add_place_payload_with("name", "address", "language");
			stepDef.user_calls_with_http_request("addPlaceAPI", "POST");
		}
		
	}
}
