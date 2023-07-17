package leaveRoom;

public class LeaveRoomResponseModel {
    private boolean wasSuccess;
    private boolean errorMessage;


    public LeaveRoomResponseModel(boolean wasSuccess, boolean errorMessage) {
        this.wasSuccess = wasSuccess;
        this.errorMessage = errorMessage;
    }

    public boolean wasSuccess() {
        return wasSuccess;
    }

    public boolean errorMessage() {
        return errorMessage;
    }
}
