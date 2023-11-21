package resources;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.AddPlace;
import pojo.Location;
import pojo.UpdatePojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    public AddPlace addPlacePayLoad(String name, String language, String address) {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;

    }

    public String deletePlacePayload(String placeId) {


        return "{\r\n\r\n    \"place_id\":\"" + placeId + "\"\r\n}\r\n";


    }

    public UpdatePojo updatePlacePayload(String address) throws IOException {

        UpdatePojo pojo = new UpdatePojo();
        pojo.setAddress(address + RandomStringUtils.randomAlphabetic(1));
        pojo.setPlaceId("9084c6ab4f9f1f6d957096461fd6824e");
        pojo.setKey("qaclick123");
        return pojo;
    }
}
