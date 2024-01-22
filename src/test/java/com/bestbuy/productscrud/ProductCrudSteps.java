package com.bestbuy.productscrud;

import com.bestbuy.bestbuyinfo.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductCrudSteps extends TestBase {
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

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create a new student")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, image, url);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Getting products")
    @Test
    public void test002() {
        ValidatableResponse response = productsSteps.getProduct(id);
        response.log().all().statusCode(200);

    }

    @Title("Updating the product")
    @Test
    public void test003() {
        name = name + "_updated";
        ValidatableResponse response = productsSteps.updateProduct(id, name, type, price, shipping, upc, description, manufacturer, model, image, url);
        response.log().all().statusCode(200);
    }

    @Title("Deleting the product and verifying the deletion")
    @Test
    public void test004(){
        productsSteps.deleteProduct(id).statusCode(204);
        productsSteps.getProduct(id).statusCode(404);

    }

}

