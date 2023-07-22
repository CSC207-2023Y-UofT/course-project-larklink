package join_room_use_case;

public class JoinByIDRequestModel {
    private final String roomID;
    private final String currUserID;

    public JoinByIDRequestModel(String roomID, String currUserID){
        this.roomID = roomID;
        this.currUserID = currUserID;
    }

    String getRoomID(){
        return roomID;
    }

    String getCurrUserID(){
        return currUserID;
    }
}
