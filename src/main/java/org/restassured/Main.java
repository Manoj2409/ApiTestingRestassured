package org.restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Main {
    public static void main(String[] args) {
    //setting the base uri
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.getBody())
        .when().post("maps/api/place/add/json").then().log().all().statusCode(200).assertThat().body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.52 (Ubuntu)"))
                .extract().response().asString();
        System.out.println("Response : "+response);

        //extract the place_id from the response
        JsonPath jsonPath=new JsonPath(response);
        String place_id=jsonPath.getString("place_id");
        System.out.println("Place id : "+place_id);


    }
}