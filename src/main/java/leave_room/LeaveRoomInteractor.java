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
    public void leaveRoom(Integer roomId, Integer currUserId) {
        // TODO: FIX
        // shouldn't fetch all rooms
        List<RoomDBModel> rooms = roomDBGateway.getRooms();

        for (RoomDBModel room : rooms) {
            if (room.getRoomID() == roomId) {
                List<Integer> activeUsers = room.getActiveUsers();
                if (activeUsers.contains(currUserId)) {
                    activeUsers.remove(currUserId);
                    leaveRoomOutputBoundary.prepareJoinOrHostView();
                }
            }
        }

        leaveRoomOutputBoundary.prepareFailedToLeaveRoomView();
    }
}
