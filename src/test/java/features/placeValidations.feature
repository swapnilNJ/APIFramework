Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify place is is being Succesfully added using AddPlaceAPI
          Given Add Place Payload with "<name>", "<language>", "<address>"
          When user calls "AddPlaceAPI" with "Post" http request
          Then the API calls is success with status code 200
          And "scope" in response body is "APP"
          And verify place_Id created maps to "<name>" using "getPlaceAPI"
          
          
 Examples: 
 |name          | language | address                     |  
 |Central house | English  | Central pathway, LMK Street |
# |Middle house  | French   | Sea Park, KenCH Street      |        


@DeletePlace
Scenario: Verify if Delete Place functionality is working

         Given DeletePlace Payload 
         When user calls "deletePlaceAPI" with "POST" http request
         Then the API call got success with status code 200
         And "status" in response body is "OK"