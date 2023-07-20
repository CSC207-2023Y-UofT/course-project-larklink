package join_room_use_case;

public class JoinByIDResponseModel {
    private boolean wasSuccess;

    public JoinByIDResponseModel(boolean wasSuccess){
        this.wasSuccess = wasSuccess;
    }

    boolean getWasSuccess(){
        return wasSuccess;
    }
}
