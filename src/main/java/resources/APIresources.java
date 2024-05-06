package resources;

public enum APIresources {
	
	addPlaceAPI("maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");
	
	
	private String resourceName;
	APIresources(String resourceName){
		this.resourceName = resourceName;
	}
	public String getResource(){
		return resourceName;
	}
}