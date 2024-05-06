package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import POJOClasses.AddPlace;
import POJOClasses.Location;

public class TestDataBuild {
	
	public static AddPlace getPlace(String name, String address, String language) {
		
		AddPlace place = new AddPlace();
		place.setAccuracy(20);
		place.setAddress(address);
		place.setLanguage(language);
		Location loc = new Location();
		loc.setLat(23.383494);
		loc.setLng(84.427362);
		place.setLocation(loc);
		place.setName(name);
		place.setPhone_number("(+91)8016934610");
		List<String> types = new ArrayList<String>(Arrays.asList("More", "Junction", "Market"));
		place.setTypes(types);
		place.setWebsite("http://google.com");
		
		return place;
	}
	
	public static String deletePlacePayload(String place_id) {
		String payloadString = 
				"{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
		return payloadString;
	}
	
	
}
