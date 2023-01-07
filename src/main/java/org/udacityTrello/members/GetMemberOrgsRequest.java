package org.udacityTrello.members;

import files.Credentials;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetMemberOrgsRequest {
    public static Response getMemberOrgsResponse(String member_ID) {
        RestAssured.baseURI = "https://api.trello.com";
        return given().pathParam("member_ID",member_ID).
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                when().get("/1/members/{member_ID}/organizations").
                then().extract().response();
    }

    public static JsonPath getMemberOrgsJs(Response response){
        return new JsonPath(response.asString());
    }

}
