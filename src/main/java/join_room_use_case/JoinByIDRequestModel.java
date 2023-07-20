package join_room_use_case;

public class JoinByIDRequestModel {
    private String roomID;
    private String currUserID;

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
