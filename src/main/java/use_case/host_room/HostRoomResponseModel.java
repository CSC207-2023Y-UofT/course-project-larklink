package use_case.host_room;

public class HostRoomResponseModel {
    private boolean wasCreated;
    private boolean errorMessage;

    public HostRoomResponseModel(boolean wasCreated, boolean errorMessage) {
        this.wasCreated = wasCreated;
        this.errorMessage = errorMessage;
    }

    public boolean getWasCreated() {
        return this.wasCreated;
    }
    public boolean getErrorMessage() {
        return this.errorMessage;
    }
}
