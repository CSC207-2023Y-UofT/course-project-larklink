package leaveRoom;

public class LeaveRoomController {
    private LeaveRoomInputBoundary leaveRoomInputBoundary;

    public LeaveRoomController(LeaveRoomInputBoundary leaveRoomInputBoundary) {
        this.leaveRoomInputBoundary = leaveRoomInputBoundary;
    }

    public void handleLeaveRoom(String roomId, String currUserId) {
        leaveRoomInputBoundary.leaveRoom(roomId, currUserId);
    }
}
