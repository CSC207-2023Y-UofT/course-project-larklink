package leave_room;

/**
 * The LeaveRoomInputBoundary interface defines the contract for leaving a room.
 * Classes that implement this interface are required to provide a leaveRoom method implementation.
 */
public interface LeaveRoomInputBoundary {

    /**
     * Performs leaving a room for the specified user.
     *
     * @param roomID The unique id of the room to leave.
     * @param userID The unique id of the user who wants to leave the room.
     */
    void leaveRoom(Integer roomID, Integer userID);
}
