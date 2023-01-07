package org.udacityTrello.organizations;

import files.Credentials;
import files.EndPoints;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.ObjectUtils;

import static io.restassured.RestAssured.given;

public class CreateOrganizationRequest {
    public static Response createOrganizationResponse() {
        RestAssured.baseURI = "https://api.trello.com";
        return given().header("Content-Type", "application/json").
                body(Payload.createOrganizationPayload()).
                queryParam("key", Credentials.getKey()).
                queryParam("token", Credentials.getToken()).
                when().post(EndPoints.getCreateOrganizationEndPoint()).
                then().extract().response();
    }

    public static JsonPath createOrgJs(Response response){
        return new JsonPath(response.asString());
    }

    public static String getOrgID(JsonPath responseJS){
            return responseJS.get("id");
    }
}
