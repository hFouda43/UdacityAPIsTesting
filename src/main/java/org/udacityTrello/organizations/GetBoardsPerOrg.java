package org.udacityTrello.organizations;

import files.Credentials;
import files.EndPoints;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBoardsPerOrg {
    public static Response getBoardsPerOrg(String org_ID) {
        RestAssured.baseURI = "https://api.trello.com";
        return given().pathParam("org_ID", org_ID).
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                when().get("/1/organizations/{org_ID}/boards").
                then().extract().response();
    }

        public static JsonPath getBoardsPerOrgsJs(Response response){
            return new JsonPath(response.asString());
        }



}
