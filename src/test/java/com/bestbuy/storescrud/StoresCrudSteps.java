package com.bestbuy.storescrud;

import com.bestbuy.bestbuyinfo.StoreSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class StoresCrudSteps extends TestBase {
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

    @Steps
    StoreSteps storeSteps;

    @Title("Creating store")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.createStores(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }
    @Title("Getting store information by ID")
    @Test
    public void test002(){
        ValidatableResponse response = storeSteps.getStores(id);
        response.log().all().statusCode(200);

    }

    @Title("Updating store information by Id")
    @Test
    public void test003() {
        name = name + "_Updated";
        storeSteps.updateStores(id, name, type, address, address2, city, state, zip, lat, lng, hours);
        ValidatableResponse response = storeSteps.getStores(id);
        response.log().all().statusCode(200);
    }

    @Title("Delete the store and verify the deletion")
    @Test
    public void test004(){
        storeSteps.deleteStores(id).statusCode(200);
        storeSteps.getStores(id).statusCode(404);
    }
    }

