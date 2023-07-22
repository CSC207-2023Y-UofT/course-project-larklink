package join_room_use_case;

public class JoinByIDRequestModel {
    private final String roomName;
    private final String currUserID;

    public JoinByIDRequestModel(String roomName, String currUserID){
        this.roomName = roomName;
        this.currUserID = currUserID;
    }

    String getRoomName(){
        return roomName;
    }

    String getCurrUserID(){
        return currUserID;
    }
}
