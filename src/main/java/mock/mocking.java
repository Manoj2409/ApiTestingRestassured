package mock;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class mocking {
    public static void main(String[] args) {
        JsonPath jsonPath=new JsonPath(payload.payload1());
        //print the number of courses
        System.out.println(jsonPath.getInt("courses.size()"));
        //print purchase amount
        System.out.println(jsonPath.getString("dashboard.purchaseAmount"));
        //print title of the first course
        System.out.println(jsonPath.getString("courses[0].title"));

        //print all the courses and respective titles
        System.out.println("//print all the courses and respective titles");
        int totalSize=jsonPath.getInt("courses.size()");
        for(int i=0;i<totalSize;i++){
            System.out.println(jsonPath.getString("courses["+i+"].title"));
            System.out.println(jsonPath.getString("courses["+i+"].price"));
        }
        //print the price of the RPA using title
        System.out.println("print the price of the RPA using title");
        for(int i=0;i<totalSize;i++){
            if(jsonPath.getString("courses["+i+"].title").equals("RPA")){
                System.out.println(jsonPath.getString("courses["+i+"].price"));//"courses["+i+"]."
                break;
            }
        }

        //validate the individual course details and purchase amount are matching
        System.out.println("validate the individual course details and purchase amount are matching");
        Integer total=0;
        for(int i=0;i<totalSize;i++){
            total=total+(jsonPath.getInt("courses["+i+"].price")*jsonPath.getInt("courses["+i+"].copies"));
        }
        //System.out.println(total);
        Integer purchasingTotal=jsonPath.getInt("dashboard.purchaseAmount");
        //System.out.println(purchasingTotal);
        Assert.assertEquals(total,purchasingTotal);


    }
}
