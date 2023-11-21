package stepDefinitions;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;
import resources.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthTest extends Utils {

    public static void main(String[] args) throws IOException, InterruptedException {

//        System.setProperty("webdriver.chrome.diver", "src/main/resources/drivers/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get(getGolbalValue("codeurl"));
//        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(getGolbalValue("email"));
//        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(getGolbalValue("passowrd"));
//        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
//        Thread.sleep(4000);
//        String url = driver.getCurrentUrl();

        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AfJohXmkJnsGiX438JZtjzKJilan1b_tp9uMh2R2YjyWNNozXWcJxSFYJ_Wwr6wwBATK4w&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);


        String accessTokenResponse = given().queryParams("code", code).urlEncodingEnabled(false)
                .queryParams("client_id", getGolbalValue("client_id"))
                .queryParams("client_secret", getGolbalValue("client_secret"))
                .queryParams("redirect_uri", getGolbalValue("redirect_uri"))
                .queryParams("grant_type", getGolbalValue("grant_type"))
                .when().log().all()
                .post(getGolbalValue("posturl")).asPrettyString();

        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");


        GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get(getGolbalValue("geturl")).as(GetCourse.class);
        System.out.println("\t");

        System.out.println("************************************************************************************");

        System.out.println("\t");
        System.out.println("The LinkedIn is : " + gc.getLinkedIn());
        System.out.println("The courses is :" + gc.getCourses());
        System.out.println("The Expertise is :" + gc.getExpertise());
        System.out.println("The Instructor is :" + gc.getInstructor());
        System.out.println("The url is :" + gc.getUrl());
        System.out.println("The services is :" + gc.getServices());
        System.out.println("\t");

        System.out.println("************* Getting key and value by using index *****************************");

        System.out.println("\t");

        System.out.println("The course title in array zero is :" + gc.getCourses().getApi().get(0).getCourseTitle());
        System.out.println("The price in array zero :" + gc.getCourses().getApi().get(0).getPrice());
        System.out.println("The course title in array one :" + gc.getCourses().getApi().get(1).getCourseTitle());
        System.out.println("The price in array one :" + gc.getCourses().getApi().get(1).getPrice());
        System.out.println("\t");

        System.out.println("************ Getting specific key without using index of Api Column **************************");

        System.out.println("\t");

        List<Api> apiCourses = gc.getCourses().getApi();
        for (int i = 0; i < apiCourses.size(); i++) {
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
                System.out.println("The price is :" + apiCourses.get(i).getPrice());
            }
        }
        System.out.println("\t");

        System.out.println("************ Getting specific key without using index of Web Automation Column **************************");

        System.out.println("\t");


        ArrayList<String> actualString = new ArrayList<String>();

        List<WebAutomation> webAutomation = gc.getCourses().getWebAutomation();

        for (int i = 0; i < webAutomation.size(); i++) {

            System.out.println("The All Course Titles In Web Automation is :" + webAutomation.get(i).getCourseTitle());

            System.out.println("The All Prices In Web Automation is :" + webAutomation.get(i).getPrice());

            actualString.add(webAutomation.get(i).getCourseTitle());
        }

        List<String> expectedString = Arrays.asList(courseTitles);

        Assert.assertTrue(actualString.equals(expectedString));


    }

}
