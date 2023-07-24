package leaveRoom;

public class LeaveRoomController {
    private LeaveRoomInputBoundary leaveRoomInputBoundary;

    public LeaveRoomController(LeaveRoomInputBoundary leaveRoomInputBoundary) {
        this.leaveRoomInputBoundary = leaveRoomInputBoundary;
    }

    public void handleLeaveRoom(Integer roomId, Integer currUserId) {
        leaveRoomInputBoundary.leaveRoom(roomId, currUserId);
    }
}
