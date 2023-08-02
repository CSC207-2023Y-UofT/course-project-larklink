package use_cases_and_adapters.join_room;

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
     * Delegates the handling of join room use case to JoinRoominteractor through JoinRoomInputBoundaru.
     *
     * @param roomName name of the chat room that user tries to join in
     */
    public void handleJoinRoomByRoomName(String roomName){
        joinRoomInputBoundary.handleJoinRoom(roomName);
    }

    /**
     * Loads all names of the existing rooms.
     *
     * @return a list of all room names
     */
    public List<String> loadRoomNames() {
        return joinRoomInputBoundary.loadRoomNames();
    }
}
