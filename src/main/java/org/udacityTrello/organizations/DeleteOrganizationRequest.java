package org.udacityTrello.organizations;

import files.Credentials;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteOrganizationRequest {
    public static Response deleteOrganizationResponse(String org_ID){
        RestAssured.baseURI = "https://api.trello.com";
        return given().pathParam("org_ID", org_ID).
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                when().delete("/1/organizations/{org_ID}").
                then().extract().response();
    }
    public static JsonPath getListsPerBoardJs(Response response){
        return new JsonPath(response.asString());
    }


}
