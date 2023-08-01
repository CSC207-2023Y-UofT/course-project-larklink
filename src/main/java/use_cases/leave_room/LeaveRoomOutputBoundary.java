package use_cases.leave_room;

/**
 * The LeaveRoomOutputBoundary interface defines the contract for setting up the view
 * after a user exits a room. Classes that implement this interface must provide
 * implementations for the prepareJoinOrHostView and prepareFailedToLeaveRoomView methods.
 */
public interface LeaveRoomOutputBoundary {

    /**
     * Prepares and sets up the view for a successful exit from the room.
     * This method is called when the leave room operation is successful.
     */
    void prepareJoinOrHostView();

    /**
     * Prepares and sets up the view for a failed exit from the room.
     * This method is called when the leave room operation fails.
     */
    void prepareFailedToLeaveRoomView();
}
