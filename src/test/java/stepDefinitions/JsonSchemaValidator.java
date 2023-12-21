package stepDefinitions;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;



public class JsonSchemaValidator {

    @Test
    public void getData() {
        baseURI = "https://reqres.in/api/";

        given()
                .get("users?page=2")
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("schema.json"))
                .log().all().statusCode(200);


    }
}
