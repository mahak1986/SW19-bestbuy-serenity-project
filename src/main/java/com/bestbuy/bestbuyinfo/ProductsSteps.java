package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class ProductsSteps {

    @Step("Create Product with name: {0},type: {1},price: {2},shipping: {3},upc: {4},description: {5},manufacturer: {6},model: {7},url: {8},image: {9}")
    public ValidatableResponse createProduct(String name, String type, double price, double shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(productsPojo)
                .when()
                .post(EndPoints.CREATE_PRODUCTS)
                .then().log().all();
    }

    @Step("Get product by id: {0}")
    public ValidatableResponse getProduct(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_PRODUCTS_BY_ID)
                .then().log().all();
    }

    @Step("Update product with id: {0},name: {1},type: {2},price: {3},shipping: {4},upc: {5},description: {6},manufacturer: {7},model: {8},url: {9},image: {10}")
    public ValidatableResponse updateProduct(int id, String name, String type, double price, double shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(productsPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCTS_BY_ID)
                .then().log().all();
    }

    @Step("Deleting the product by id : {0}")
    public ValidatableResponse deleteProduct(int id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .delete(EndPoints.DELETE_PRODUCTS_BY_ID)
                .then().log().all().statusCode(204);
    }
    @Step("Verifying if the product has been deleted")
    public ValidatableResponse getProductById(int id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_PRODUCTS_BY_ID)
                .then();
    }



}
