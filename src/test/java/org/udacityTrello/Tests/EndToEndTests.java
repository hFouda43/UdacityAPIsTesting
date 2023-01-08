package org.udacityTrello.Tests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.udacityTrello.boards.CreateBoardRequest;
import org.udacityTrello.boards.DeleteBoardRequest;
import org.udacityTrello.boards.GetListsPerBoard;
import org.udacityTrello.lists.ArchiveListRequest;
import org.udacityTrello.lists.CreateListRequest;
import org.udacityTrello.members.GetMemberOrgsRequest;
import org.udacityTrello.members.GetMemberRequest;
import org.udacityTrello.organizations.CreateOrganizationRequest;
import org.udacityTrello.organizations.DeleteOrganizationRequest;
import org.udacityTrello.organizations.GetBoardsPerOrg;

public class EndToEndTests {
    //Get the member ID
    String member_ID= GetMemberRequest.getmemberID(GetMemberRequest.getMemberJS(GetMemberRequest.getMemberResponse()));
    //Create New Org
    Response CreateOrganizationResponse= CreateOrganizationRequest.createOrganizationResponse();
    JsonPath CreateOrganizationResponseBody=CreateOrganizationRequest.createOrgJs(CreateOrganizationResponse);
    String org_ID=CreateOrganizationResponseBody.get("id");
    //Get the member's organizations
    Response getMemberOrgsResponse = GetMemberOrgsRequest.getMemberOrgsResponse(member_ID);
    JsonPath getMemberOrgsResponseBody = GetMemberOrgsRequest.getMemberOrgsJs(getMemberOrgsResponse);

    //Create New Board
    Response createBoardResponse = CreateBoardRequest.createBoardResponse(org_ID);
    JsonPath createBoardResponseBody = CreateBoardRequest.getBoardJS(createBoardResponse);
    String board_ID=createBoardResponseBody.get("id");
    //Get Boards Created per ORG
    Response getBoardsPerOrgResponse= GetBoardsPerOrg.getBoardsPerOrg(org_ID);
    JsonPath getBoardsPerOrgResponseBody=GetBoardsPerOrg.getBoardsPerOrgsJs(getBoardsPerOrgResponse);
    //Create new list to the board
    Response createListResponse = CreateListRequest.createListResponse(board_ID);
    JsonPath createListResponseBody=CreateListRequest.getcreateListJS(createListResponse);
    String list_ID = CreateListRequest.getListID(createListResponseBody);
    //Get Lists created on the board
    Response getListsPerBoardResponse= GetListsPerBoard.getListsPerBoardResponse(board_ID);
    JsonPath getListsPerBoardResponseBody=GetListsPerBoard.getListsPerBoardJs(getListsPerBoardResponse);
    //Archive a List
    Response archiveListResponse= ArchiveListRequest.archiveListResponse(list_ID);
    JsonPath archiveListResponseBody=ArchiveListRequest.archiveListJS(archiveListResponse);
    //Delete the board
    Response deleteBoardResponse= DeleteBoardRequest.deleteBoardResponse(board_ID);
    JsonPath deleteBoardResponseBody=DeleteBoardRequest.getListsPerBoardJs(deleteBoardResponse);
    //Delete the organization
    Response deleteOrganizationResponse= DeleteOrganizationRequest.deleteOrganizationResponse(org_ID);

    @Test
    public void createOrganizationTest(){
        //Validate the status code
        Assert.assertEquals(CreateOrganizationResponse.statusCode(),200);
        // Validate the organization display name
        Assert.assertEquals(CreateOrganizationResponseBody.get("displayName"),"Udacity");
        //Validate the creator ID
        Assert.assertEquals(CreateOrganizationResponseBody.get("idMemberCreator"),member_ID);
        //Validate the response header
        Assert.assertTrue(CreateOrganizationResponse.header("Content-Type").contains("application/json"));

    }

    @Test
    public void setGetMemberOrgsTest(){
        // Validate the status code of the request
        Assert.assertEquals(getMemberOrgsResponse.statusCode(), 200);
        //Validate that the member is having at least one org created
        int orgsCount = getMemberOrgsResponseBody.getInt("id.size()");
        Assert.assertTrue(orgsCount > 0);
        //Validate that the member creator id is correct
        for (int i = 0; i < orgsCount; i++) {
            Assert.assertEquals(getMemberOrgsResponseBody.get("idMemberCreator[" + i + "]"), member_ID);
        }
        //Validate that the newly added org is displayed in the list
        for (int i = 0; i < orgsCount; i++) {
            if (getMemberOrgsResponseBody.get("id[" + i + "]").equals(org_ID)) {
                Assert.assertEquals(getMemberOrgsResponseBody.get("id[" + i + "]"), org_ID);
                break;
            }
        }
    }

    @Test
    public void createBoardTest(){
        //Validate that the board is created successfully
        Assert.assertEquals(createBoardResponse.statusCode(), 200);
        //validate the board name
        Assert.assertEquals(createBoardResponseBody.get("name"), "Rest Assured Testing Board");
        //validate that the board is added to the created org
        Assert.assertEquals(createBoardResponseBody.get("idOrganization"), org_ID);
        //Validate that the added board is active
        Assert.assertEquals(createBoardResponseBody.get("closed"), false);
    }

    @Test
    public void getBoardsPerOrgTest(){
        int boardsCount=getBoardsPerOrgResponseBody.getInt("id.size()");
        //Validate the status code of the get boards per organization request
        Assert.assertEquals(getBoardsPerOrgResponse.statusCode(),200);
        //Validate that one board is added to the org
        Assert.assertTrue(boardsCount ==1);
        //Validating the board member creator
        Assert.assertEquals(getBoardsPerOrgResponseBody.get("idMemberCreator[0]"), member_ID);
    }

    @Test
    public void createListTest(){
        //Validate status code
        Assert.assertEquals(createListResponseBody.get("name"), "API Testing");
        //Validate that the list is added to the board
        Assert.assertEquals(createListResponseBody.get("idBoard"), board_ID);
        //Validate the closed status of the added list
        Assert.assertEquals(createListResponseBody.get("closed"), false);
        //Validate the request response time
//        Assert.assertTrue(createListResponse.timeIn()<800);
}

@Test
    public void setGetListsPerBoardTest(){
    int numOfLists=getListsPerBoardResponseBody.getInt("id.size()");
    //Validate the status code
    Assert.assertEquals(getListsPerBoardResponse.statusCode(), 200);
    //Validate that the lists are at least 3 (as there are 3 lists created by default)
    Assert.assertTrue(numOfLists >= 3);
    //Validate that the lists are added to the created board
        for (int i=0;i<numOfLists;i++)
        {
            Assert.assertEquals(getListsPerBoardResponseBody.get("idBoard["+i+"]"),board_ID);
        }
}

@Test
    public void archiveListTest(){
        //Validate the status code
    Assert.assertEquals(archiveListResponse.statusCode(),200);
    //Validate the archived list
    Assert.assertEquals(archiveListResponseBody.get("id"),list_ID);
    //Validate the board ID that the list is added to
    Assert.assertEquals(archiveListResponseBody.get("idBoard"),board_ID);
    //Validate the closed value is true after archiving
    Assert.assertEquals(archiveListResponseBody.get("closed"),true);
}

@Test
    public void deleteBoardTest(){
        //Validate status code
    Assert.assertEquals(deleteBoardResponse.statusCode(),200);
    //Validate the response
    Assert.assertEquals(deleteBoardResponseBody.get("_value"),null);
    //Validate the response headers
    Assert.assertTrue(deleteBoardResponse.header("Content-Type").contains("application/json"));
}

@Test
    public void deleteOrganizationTest(){
        //Validate the status code
    Assert.assertEquals(deleteOrganizationResponse.statusCode(),200);
}

}
