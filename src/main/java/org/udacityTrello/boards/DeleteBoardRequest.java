package org.udacityTrello.boards;

import files.Credentials;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {
    public static Response deleteBoardResponse(String board_ID){
        RestAssured.baseURI = "https://api.trello.com";
        return given().pathParam("board_ID", board_ID).
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                when().delete("/1/boards/{board_ID}").
                then().extract().response();
    }
    public static JsonPath getListsPerBoardJs(Response response){
        return new JsonPath(response.asString());
    }


}
