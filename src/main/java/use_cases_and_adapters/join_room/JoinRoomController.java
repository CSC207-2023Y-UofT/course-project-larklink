package use_cases_and_adapters.join_room;

import java.util.List;

/**
 * A controller for join room use case.
 */
public class JoinRoomController {

    private final JoinRoomInputBoundary joinRoomInputBoundary;

    /**
     * Constructs new JoinRoomController object with given input boundary
     *
     * @param joinRoomInputBoundary a JoinRoomInputBoundary object
     */
    public JoinRoomController(JoinRoomInputBoundary joinRoomInputBoundary){
        this.joinRoomInputBoundary = joinRoomInputBoundary;
    }

    /**
     * Delegates the handling of join room use case to JoinRoomInteractor through JoinRoomInputBoundary.
     *
     * @param roomName name of the chat room that user tries to join in
     */
    public void handleJoinRoomByRoomName(String roomName){
        joinRoomInputBoundary.handleJoinRoom(roomName);
    }

    /**
     * Retrieves all names of the existing rooms.
     *
     * @return a list of all room names
     */
    public List<String> loadRoomNames() {
        return joinRoomInputBoundary.loadRoomNames();
    }
}
