package org.udacityTrello.lists;

import files.Credentials;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ArchiveListRequest {
    public static Response archiveListResponse(String list_ID) {
        RestAssured.baseURI = "https://api.trello.com";
        return given().pathParam("list_ID",list_ID).
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                queryParam("value",true).
                when().put("/1/lists/{list_ID}/closed").
                then().extract().response();
    }

    public static JsonPath archiveListJS(Response response){
        return new JsonPath(response.asString());
    }

}
