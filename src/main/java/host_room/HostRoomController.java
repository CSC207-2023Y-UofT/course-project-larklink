package host_room;

import leaveRoom.RoomModel;

import java.util.ArrayList;
import java.util.List;

public class HostRoomController {
    private final HostRoomInputBoundary inputBoundary;

    public HostRoomController(HostRoomInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleHostRoom(Integer host) {
        List<Integer> activeUsers = new ArrayList<>(host);
        RoomModel request = new RoomModel(activeUsers, host);
        inputBoundary.hostRoom(request);
    }
}
