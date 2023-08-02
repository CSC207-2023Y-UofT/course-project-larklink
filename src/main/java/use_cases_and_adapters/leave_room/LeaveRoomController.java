package use_cases_and_adapters.leave_room;

/**
 * The logic for leaving a room is handled by the LeaveRoomController class *
 * by invoking the leaveRoom method on the supplied LeaveRoomInputBoundary object.
 */
public class LeaveRoomController {
    private final LeaveRoomInputBoundary leaveRoomInputBoundary;

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
     */
    public void handleLeaveRoom() {
        leaveRoomInputBoundary.leaveRoom();
    }
}

