package join_room;

import java.util.List;

/**
 * A controller for join room use case.
 */
public class JoinRoomController {

    private final JoinRoomInputBoundary joinRoomInputBoundary;

    public JoinRoomController(JoinRoomInputBoundary joinRoomInputBoundary){
        this.joinRoomInputBoundary = joinRoomInputBoundary;
    }

    /**
     * Handles join room use case through JoinRoomInputBoundary.
     *
     * @param roomName name of the chat room that user tries to join in
     */
    public void handleJoinRoomByRoomName(String roomName){
        joinRoomInputBoundary.handleJoinRoom(roomName);
    }

    /**
     * Loads all names of the existing room
     */
    public List<String> loadRoomNames() {
        return joinRoomInputBoundary.loadRoomNames();
    }
}
