package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JiraTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8080";

        //Login Scenario

        SessionFilter session = new SessionFilter();

        String response = given().header("Content-Type", "application/json").body("{\r\n" +

                "    \"username\": \"bharathvk7\",\r\n" +

                "    \"password\": \"Themagic@2023#\"\r\n" +

                "}").log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();

        //Add Comment
        String addComment = given().pathParam("key", "10106").log().all().header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \" This is my First Automation Comment\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
        JsonPath jpath = new JsonPath(addComment);
        String commentID = jpath.getString("id");


        //Add Attachment

        given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("key", "10106")

                .header("Content-Type", "multipart/form-data")

                .multiPart("file", new File("jira.txt")).when().

                post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);


        //Get Issue

        String issueDetails = given().filter(session).pathParam("key", "10106").
                queryParam("fields", "comment").get("/rest/api/2/issue/{key}")
                .then().log().all().extract().response().asPrettyString();
        System.out.println("The Get Issue is : " + issueDetails);

        JsonPath js = new JsonPath(issueDetails);
        int commentCount = js.getInt("fields.comment.comments.size()");
        for (int i = 0; i < commentCount; i++) {
            System.out.println(js.getInt("fields.comment.comments.size[" + i + "].id"));
        }

    }


}

