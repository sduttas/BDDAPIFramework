#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Validating Place API's
  @addPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<address>" "<language>"
		When user calls "addPlaceAPI" with "post" http request
		Then The API call is success with status code 200
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify place_id created maps to "<name>" calling "getPlaceAPI" with "get" http request
		Then The API call is success with status code 200
		
		Examples:
			|		name	 |	 		address			 |				language			 |
			|Alaka abasan|		Newtown, WB	 |				Bengali				 |
		
	
	@deletePlace
	Scenario: Verify id delete place functionality is working fine
		Given deletePlace Payload
		When user calls "deletePlaceAPI" with "delete" http request
		Then The API call is success with status code 200
		And "status" in response body is "OK"