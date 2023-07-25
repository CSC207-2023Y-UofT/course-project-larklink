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
     * @param host the userID of the room's host
     */

    public void handleHostRoom(Integer host, String name) {
        List<Integer> activeUsers = new ArrayList<>(host);
        RoomModel request = new RoomModel(activeUsers, host, name);
        inputBoundary.hostRoom(request);
    }
}
