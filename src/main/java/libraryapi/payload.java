package libraryapi;

public class payload {
    public static String postBody1(String isbn, String aisle){
        return "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foer\"\n" +
                "}";
    }

    public static String deleteBody1(String responseID){
        return "{\n" +
                "    \"ID\": \""+responseID+"\"\n" +
                "}";
    }
}
