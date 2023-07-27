package leave_room;

/**
 * The logic for leaving a room is handled by the LeaveRoomController class *
 * by invoking the leaveRoom method on the supplied LeaveRoomInputBoundary object.
 */
public class LeaveRoomController {
    private LeaveRoomInputBoundary leaveRoomInputBoundary;

    /**
     * Using the supplied LeaveRoomInputBoundary, creates a new LeaveRoomController.
     *
     * @param leaveRoomInputBoundary the input boundary in charge of processing requests for leave rooms.
     */
    public LeaveRoomController(LeaveRoomInputBoundary leaveRoomInputBoundary) {
        this.leaveRoomInputBoundary = leaveRoomInputBoundary;
    }

    /**
     * Handles the request to leave a room.
     *
     * @param roomID The unique id of the room to leave.
     * @param userID The unique id of the user who wants to leave the room.
     */
    public void handleLeaveRoom(Integer roomID, Integer userID) {
        leaveRoomInputBoundary.leaveRoom(roomID, userID);
    }
}

