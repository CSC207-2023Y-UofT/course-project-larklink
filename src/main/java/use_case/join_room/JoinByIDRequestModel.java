package use_case.join_room;

public class JoinByIDRequestModel {
    private final String name;
    private final Integer userID;

    public JoinByIDRequestModel(String name, Integer userID){
        this.name = name;
        this.userID = userID;
    }

    String getName(){
        return name;
    }

    int getUserID(){
        return userID;
    }
}
