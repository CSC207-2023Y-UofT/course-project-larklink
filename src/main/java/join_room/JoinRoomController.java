package join_room;

/**
 * A controller for join room use case.
 */
public class JoinRoomController {

    private final JoinRoomInputBoundary joinRoomInputBoundary;

    public JoinRoomController(JoinRoomInputBoundary joinRoomInputBoundary){
        this.joinRoomInputBoundary = joinRoomInputBoundary;
    }

    /**
     * Formats a JoinRoomRequestModel instance using given name and userID
     * and handles join room use case through JoinRoomInputBoundary.
     *
     * @param roomName name of the chat room that user tries to join in
     */
    public void formatAndHandleJoinRoom(String roomName){
        joinRoomInputBoundary.handleJoinRoom(roomName);
    }
}
