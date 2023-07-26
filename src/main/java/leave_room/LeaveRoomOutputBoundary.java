package leave_room;

/**
 * After a user exits a room, the contract for setting up the view is defined by the LeaveRoomOutputBoundary interface.
 * The prepareHostOrJoinView function must be implemented by classes that implement this interface.
 */
public interface LeaveRoomOutputBoundary {

    /**
     * Processes the supplied LeaveRoomResponseModel to prepare the view once a user exits a room.
     *
     * @param responseModel The response model includes data on the operation's
     *                      success or failure in the leave room as well as any other information required for the view.
     */
    void prepareHostOrJoinView(LeaveRoomResponseModel responseModel);
}
