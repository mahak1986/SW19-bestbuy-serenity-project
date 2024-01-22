package com.bestbuy.storescrud;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
@RunWith(SerenityRunner.class)
public class StoresCrudTest extends TestBase {

    static String name = TestUtils.getRandomValue() + "Tonka";
    static String type = TestUtils.getRandomValue() + "Fieldbox";
    static String address = TestUtils.getRandomValue() + "123 Wayfair Road";
    static String address2 = TestUtils.getRandomValue() + "Disneyland";
    static String city = "Harrow";
    static String state = "London";
    static String zip = "12345";
    static Double lat = 14.9999;
    static Double lng = -23.8888;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    static int id;

    @Title("This will create a new store")
    @Test
    public void test001() { //post method
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post("/stores");
        response.prettyPrint();
        response.then().statusCode(201);

    }
    @Title("Getting stores")
    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/stores");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void test003() {
        StorePojo storePojo = new StorePojo();
        storePojo.setZip("34568");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .patch("/stores");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void test004(){
        Response response= given()
                .when()
                .delete("/stores");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }
}
