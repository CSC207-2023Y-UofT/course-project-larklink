package use_cases.leave_room;

/**
 * The LeaveRoomInputBoundary interface defines the contract for leaving a room.
 * Classes that implement this interface are required to provide a leaveRoom method implementation.
 */
public interface LeaveRoomInputBoundary {

    /**
     * Performs leaving a room for the specified user.
     */
    void leaveRoom();
}
