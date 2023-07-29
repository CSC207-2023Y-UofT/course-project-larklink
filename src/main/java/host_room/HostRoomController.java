package host_room;

import models.RoomModel;

import java.util.ArrayList;
import java.util.List;

public class HostRoomController {
    private final HostRoomInputBoundary inputBoundary;

    public HostRoomController(HostRoomInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Creates a RoomModel using the userID of the specified host and delegates the handling to HostRoomInteractor
     * through HostRoomInputBoundary.
     */
    public void handleHostRoom(String roomName) {
        inputBoundary.hostRoom(roomName);
    }
}
