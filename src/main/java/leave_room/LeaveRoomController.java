package leave_room;

public class LeaveRoomController {
    private LeaveRoomInputBoundary leaveRoomInputBoundary;

    public LeaveRoomController(LeaveRoomInputBoundary leaveRoomInputBoundary) {
        this.leaveRoomInputBoundary = leaveRoomInputBoundary;
    }

    public void handleLeaveRoom(Integer roomID, Integer currUserId) {
        leaveRoomInputBoundary.leaveRoom(roomID, currUserId);
    }
}
