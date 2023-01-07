package org.udacityTrello.lists;

import files.Credentials;
import files.EndPoints;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createListResponse(String board_Id) {
        RestAssured.baseURI = "https://api.trello.com";
        return given().header("Content-Type", "application/json").
                body(Payload.createListPayload(board_Id)).
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                when().post(EndPoints.createListEndPoint()).
                then().extract().response();
    }

    public static JsonPath getcreateListJS(Response response){
        return new JsonPath(response.asString());
    }

    public static String getListID(JsonPath responseBody){
        return responseBody.get("id");

    }
}
