package org.udacityTrello.members;

import files.Credentials;
import files.EndPoints;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetMemberRequest {
    public static Response getMemberResponse() {
        RestAssured.baseURI = "https://api.trello.com";
        return given().
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                when().get(EndPoints.getMemberEndpoint()).
                then().extract().response();
    }
    public static JsonPath getMemberJS(Response response){
        return new JsonPath(response.asString());
    }

    public static String getmemberID(JsonPath responseJS){
            return responseJS.get("id");

    }
}
