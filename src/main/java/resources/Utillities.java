package resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import POJOClasses.AddPlace;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utillities {
	public static Response callAddPlaceAPI(AddPlace place, String resourceName) throws IOException {
		RestAssured.baseURI = getGlobalValue("baseURI");
		Response response =

				given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
						.body(place).when().post(resourceName).then().log().all()
						.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response();

		return response;
	}

	public static Response callDeletePlaceAPI(String deletePayload, String resourceName) throws IOException {
		RestAssured.baseURI = getGlobalValue("baseURI");

		Response response = given().log().all()
				.queryParam("key", "qaclick123")
				.body(deletePayload)
				.when().get(resourceName)
				.then().log().all()
				.extract().response();

		return response;
	}

	public static Response callGetPlaceAPI(String place_id, String resourceName) throws IOException {
		RestAssured.baseURI = getGlobalValue("baseURI");

		Response response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).when()
				.get(resourceName).then().log().all().extract().response();

		return response;
	}

	public static String getValueFromResponseBody(Response response, String key) {
		String responseAsString = response.asString();
		JsonPath jsPath = new JsonPath(responseAsString);
		String value = jsPath.getString(key);
		return value;
	}

	public static String getGlobalValue(String key) throws IOException {
		String filePath = "C:\\Users\\sudutta\\eclipse-workspace\\BDDAPIFramework\\src\\main\\java\\resources\\global.properties";
		Properties prop = new Properties();
		FileInputStream inp = new FileInputStream(filePath);
		prop.load(inp);
		return prop.getProperty(key);
	}

}
