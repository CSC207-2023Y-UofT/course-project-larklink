package join_room;

public class JoinByIDRequestModel {
    private final String roomName;
    private final int currUserID;

    public JoinByIDRequestModel(String roomName, int currUserID){
        this.roomName = roomName;
        this.currUserID = currUserID;
    }

    String getRoomName(){
        return roomName;
    }

    int getCurrUserID(){
        return currUserID;
    }
}
