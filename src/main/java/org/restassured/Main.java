package org.restassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Main {
    public static void main(String[] args) {
    //setting the base uri
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.getBody())
                .when().post("maps/api/place/add/json").then().log().all().statusCode(200).assertThat().body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.52 (Ubuntu)"));
    }
}