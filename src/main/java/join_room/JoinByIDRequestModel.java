package join_room;

public class JoinByIDRequestModel {
    private final String name;
    private final int currUserID;

    public JoinByIDRequestModel(String name, Integer currUserID){
        this.name = name;
        this.currUserID = currUserID;
    }

    String getName(){
        return name;
    }

    int getCurrUserID(){
        return currUserID;
    }
}
