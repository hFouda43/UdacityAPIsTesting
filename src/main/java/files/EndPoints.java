package files;

public class EndPoints {
    private static String createOrgEndPoint="/1/organizations";
    private static String getMemberEndPoint="/1/members/me";
    private static String getMemberOrgsEndpointP1="/1/members/";
    private static String getMemberOrgsEndpointP2="/organizations";
    private static String createBoardEndPoint="/1/boards/";
    private static String getBoardsPerOrgEndPoint="/1/organizations/{{organization_ID}}/boards";
    private static String createListEndPoint="/1/lists";
    public static String getCreateOrganizationEndPoint(){
        return createOrgEndPoint;
    }
    public static String getMemberEndpoint(){
        return getMemberEndPoint;
    }
//    public static String getMemberOrgsEndpoint(String memberID){
//        return getMemberOrgsEndpointP1+memberID+getMemberOrgsEndpointP2;
//    }
    public static String createBoardEndPoint(){
        return createBoardEndPoint;
    }
    public static String getBoardsPerOrgEndPoint(){
        return getBoardsPerOrgEndPoint;
    }
    public static String createListEndPoint(){return createListEndPoint;}

}
