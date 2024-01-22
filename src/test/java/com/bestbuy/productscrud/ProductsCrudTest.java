package com.bestbuy.productscrud;

import com.bestbuy.model.ProductPojo;
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
public class ProductsCrudTest extends TestBase {
    static String name = "Duracell - AAA Batteries (4-Pack)" + TestUtils.getRandomValue();
    static String type = "HardGood" + TestUtils.getRandomValue();
    static Double price = 5.49;
    static String upc = "041333424019";
    static Double shipping = 0.0;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int id;



    @Title("This will create a new product")
    @Test
    public void test001() {
        ProductPojo productpojo = new ProductPojo();
        productpojo.setName(name);
        productpojo.setType(type);
        productpojo.setPrice(price);
        productpojo.setUpc(upc);
        productpojo.setShipping(shipping);
        productpojo.setDescription(description);
        productpojo.setManufacturer(manufacturer);
        productpojo.setModel(model);
        productpojo.setImage(image);
        productpojo.setUrl(url);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productpojo)
                .post("/products");
        id = response.then().contentType(ContentType.JSON).extract().path("id");
        response.then().statusCode(201);
        System.out.println("Id no is :" + id);
        response.prettyPrint();
    }
    @Title("Getting products")
    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Title("Updating the product")
    @Test
    public void test003(){
        ProductPojo productpojo = new ProductPojo();
        productpojo.setName(name);
        productpojo.setType(type);
        productpojo.setPrice(10.00);
        productpojo.setUpc(upc);
        productpojo.setShipping(shipping);
        productpojo.setDescription(description);
        productpojo.setManufacturer(manufacturer);
        productpojo.setModel(model);
        productpojo.setImage(image);
        productpojo.setUrl(url);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productpojo)
                .patch("/products" + "/" + id);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Title("Verifying if the product has been updated")
    @Test
    public void test004() {
        Response response = given()
                .when()
                .get("/products" + "/" + id);
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Title("This will delete the product")
    @Test
    public void test005() {
        Response response = given()
                .when()
                .delete("/products" +  id);
        response.prettyPrint();
        response.then().log().all().statusCode(404);


    }

}

