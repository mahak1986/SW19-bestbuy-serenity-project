package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class StoreSteps {
    @Step("Creating Store with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}, lng: {8}, hours: {9} and services {10}")
    public ValidatableResponse createStores(String name, String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours) {
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
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(storePojo)
                .when()
                .post(EndPoints.CREATE_STORES)
                .then().log().all();
    }
    @Step("Getting the store with Id : {0} ")
    public ValidatableResponse getStores(int id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_STORES_BY_ID)
                .then().log().all();
    }
    @Step("Updating Store with with id: {0}, name: {1}, type: {2}, address: {3}, address2: {4}, city: {5}, state: {6}, zip: {7}, lat: {8}, lng: {9}, hours: {10} and services {11}")
    public ValidatableResponse updateStores(int id, String name, String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours) {
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
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(storePojo)
                .when()
                .put(EndPoints.UPDATE_STORES_BY_ID)
                .then().log().all();
    }

    @Step("Deleting the store with id :{0}")
    public ValidatableResponse deleteStores(int id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then();
    }

    }


