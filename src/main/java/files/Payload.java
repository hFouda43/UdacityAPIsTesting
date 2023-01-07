package files;

public class Payload {

    public static String createOrganizationPayload() {
        return "{\"displayName\":\"Udacity\",\n" +
                "\"desc\":\"Creating a test ORganization for API automation testing project\",\n" +
                "\"name\":\"api automation testing\"\n" +
                "}";
    }

public static String createBoardPayload(String org_ID){
        return "{\n" +
                "    \"name\":\"Rest Assured Testing Board\",\n" +
                "\"idOrganization\":\""+org_ID+"\"\n" +
                "}";
}

public static String createListPayload(String board_ID){
        return "{\n" +
                "    \"name\":\"API Testing\",\n" +
                "    \"idBoard\":\""+board_ID+"\"\n" +
                "}";
}
}
