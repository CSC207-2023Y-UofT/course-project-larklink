package leave_room;

import database.RoomDBGateway;
import models.RoomDBModel;

import java.util.List;

public class LeaveRoomInteractor implements LeaveRoomInputBoundary {
    private final RoomDBGateway roomDBGateway;
    private final LeaveRoomOutputBoundary leaveRoomOutputBoundary;

    public LeaveRoomInteractor(RoomDBGateway roomDBGateway, LeaveRoomOutputBoundary leaveRoomOutputBoundary) {
        this.roomDBGateway = roomDBGateway;
        this.leaveRoomOutputBoundary = leaveRoomOutputBoundary;
    }

    @Override
    public void leaveRoom(Integer roomID, Integer currUserId) {
        RoomDBModel room = roomDBGateway.getARoom(roomID);
        List<Integer> activeUsers = room.getActiveUsers();
        if (activeUsers.contains(currUserId)) {
            activeUsers.remove(currUserId);
            room.setActiveUsers(activeUsers);
            roomDBGateway.leaveARoom(room);
            leaveRoomOutputBoundary.prepareJoinOrHostView(currUserId);
            return;
        }
        leaveRoomOutputBoundary.prepareFailedToLeaveRoomView();
    }
}
