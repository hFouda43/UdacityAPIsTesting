package org.udacityTrello.boards;

import files.Credentials;
import files.EndPoints;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.udacityTrello.organizations.CreateOrganizationRequest;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {
    public static Response createBoardResponse(String org_ID) {
        RestAssured.baseURI = "https://api.trello.com";
        return given().header("Content-Type", "application/json").
                body(Payload.createBoardPayload(org_ID)).
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                when().post(EndPoints.createBoardEndPoint()).
                then().extract().response();
    }

    public static JsonPath getBoardJS(Response response){
        return new JsonPath(response.asString());
    }

    public static String getBoardID(Response response){
        return getBoardJS(response).get("id");

    }
}
