package libraryapi;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DynamicJsonPayload {
    @DataProvider(name = "BooksData")
    public Object[][] testData(){
        String test[][]={{"back","1234"},{"no","1212"},{"what","8749"}};
        return test;
    }


    @Test(dataProvider = "BooksData")
    public void test(String isbn, String aisle){
        //post method
        baseURI="https://rahulshettyacademy.com";
        String response=
        given().log().all().header("Content-Type","application/json")
                .body(payload.postBody1(isbn,aisle))
        .when().post("Library/Addbook.php").
        then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath jsonPath=new JsonPath(response);
        String responseId=jsonPath.getString("ID");
        System.out.println("Response ID : "+responseId);

        //delete method
        String deleteResponse=
        given().log().all().header("Content-Type","application/json")
                .body(payload.deleteBody1(responseId))
        .when().delete("Library/DeleteBook.php")
        .then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println("Delete Response : "+deleteResponse);
    }


}
