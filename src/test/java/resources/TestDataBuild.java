package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayLoad(String name, String language, String address){
		
		
		AddPlace AP = new AddPlace();
		AP.setAccuracy(50);
		AP.setAddress(address);
		AP.setLanguage(language);
		AP.setWebsite("http://google.com");
		AP.setPhone_number("(+91) 983 893 3937");
		AP.setName(name);
		// For Type list we need to create List object to set value
		List<String> MyList = new ArrayList<String>();
		MyList.add("Shoe Park");
		MyList.add("Shop");
		AP.setTypes(MyList);
		
		//For Location array we need to create object of same class, Location class
		Location LC = new Location();
		
		LC.setLat(-38.383494);
		LC.setLng(33.427362);
		
		AP.setLocation(LC);  // sub Json injected to main class or json
		
		return AP;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
