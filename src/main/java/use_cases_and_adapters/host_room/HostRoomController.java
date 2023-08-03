package use_cases_and_adapters.host_room;

public class HostRoomController {
    private final HostRoomInputBoundary inputBoundary;

    public HostRoomController(HostRoomInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Creates a RoomModel using the specified roomName by delegating to HostRoomInteractor through HostRoomInputBoundary.
     */
    public void handleHostRoom(String roomName) {
        inputBoundary.hostRoom(roomName);
    }
}
