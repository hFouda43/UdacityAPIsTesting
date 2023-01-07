package org.udacityTrello.boards;

import files.Credentials;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.udacityTrello.members.GetMemberRequest;

import static io.restassured.RestAssured.given;

public class GetListsPerBoard {
public static Response getListsPerBoardResponse(String board_ID){
    RestAssured.baseURI = "https://api.trello.com";
    return given().pathParam("board_ID", board_ID).
            queryParam("key", Credentials.getKey()).
            queryParam("token", Credentials.getToken()).
            when().get("/1/boards/{board_ID}/lists").
            then().extract().response();
}
    public static JsonPath getListsPerBoardJs(Response response){
        return new JsonPath(response.asString());
    }


}
