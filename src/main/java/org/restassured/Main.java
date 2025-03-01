package org.restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Main {
    public static void main(String[] args) {

        //setting the base uri
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.getBody1())
        .when().post("maps/api/place/add/json").then().log().all().statusCode(200).assertThat().body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.52 (Ubuntu)"))
                .extract().response().asString();
        System.out.println("Response : "+response);

        //extract the place_id from the response
        JsonPath jsonPath1=new JsonPath(response);
        String place_id=jsonPath1.getString("place_id");
        System.out.println("Place id : "+place_id) ;

        //update place
        String newAddress = "Summer Walk, Africa";

            given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(payload.getBody2(place_id,newAddress)).
                when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //get the updated place and validate whether the place is updated
        String getResponse=given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id",place_id)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().asString();

        JsonPath jsonPath2=new JsonPath(getResponse);
        String newAddressResponse=jsonPath2.getString("address");
        System.out.println(newAddressResponse);
        Assert.assertEquals(newAddressResponse,newAddress);

    }
}