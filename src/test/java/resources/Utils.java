package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {

        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGolbalValue("baseurl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public static String getGolbalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream stream = new FileInputStream("src\\test\\java\\resources\\global.properties");
        properties.load(stream);
        return properties.getProperty(key);

    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        System.out.println(resp);
        return js.get(key).toString();


    }

    public RequestSpecification requestSpecificationUpdate() throws IOException {

        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGolbalValue("baseurl")).addQueryParam("place_id","9084c6ab4f9f1f6d957096461fd6824e").addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

}
