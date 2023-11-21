package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PocRestAssuredDemo extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;

    String actualMessgae;

    public static String place_id;


    TestData data = new TestData();

    @Given("add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));

    }


    @When("user calls the {string} using http {string} request")
    public void user_calls_the_using_http_request(String resource, String httpMethod) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResources());
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResources());
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResources());
        } else if (httpMethod.equalsIgnoreCase("PUT")) {
            response = res.when().put(resourceAPI.getResources());
        }
        String responseString = response.asPrettyString();
        System.out.println(responseString);

    }

    @And("the api call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {

        assertEquals(getJsonPath(response, key), expectedValue);
    }

    @Then("verify Place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

        //requestSpecification
        place_id = getJsonPath(response, "place_id");
        res = given(requestSpecification()).queryParam("place_id", place_id);
        user_calls_the_using_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

    @Given("update place payload with {string}")
    public void updatePlacePayloadWith(String address) throws IOException {
        res = given().spec(requestSpecificationUpdate()).body(data.updatePlacePayload(address));
    }

    @Then("verify the {string} in response body is {string}")
    public void verifyTheInResponseBodyIs(String msg, String expectedValue) {
        assertEquals(getJsonPath(response, msg), expectedValue);
    }


}


