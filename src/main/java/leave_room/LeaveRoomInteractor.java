package leave_room;

import database.RoomDBGateway;
import models.RoomDBModel;

import java.util.List;

public class LeaveRoomInteractor implements LeaveRoomInputBoundary {
    private RoomDBGateway roomDBGateway;
    private LeaveRoomOutputBoundary leaveRoomOutputBoundary;

    public LeaveRoomInteractor(RoomDBGateway roomDBGateway, LeaveRoomOutputBoundary leaveRoomOutputBoundary) {
        this.roomDBGateway = roomDBGateway;
        this.leaveRoomOutputBoundary = leaveRoomOutputBoundary;
    }

    @Override
    public void leaveRoom(Integer roomId, Integer currUserId) {
        // TODO: FIX
        // shouldn't fetch all rooms
        List<RoomDBModel> rooms = roomDBGateway.retrieveEveryRoom();

        for (RoomDBModel room : rooms) {
            if (room.getRoomID() == roomId) {
                List<Integer> activeUsers = room.getActiveUsers();
                if (activeUsers.contains(currUserId)) {
                    activeUsers.remove(currUserId);
                    //roomDBGateway.updateRoomActiveUsers(room);
                    leaveRoomOutputBoundary.prepareHostOrJoinView(new LeaveRoomResponseModel(true, false));
                }
            }
        }

        leaveRoomOutputBoundary.prepareHostOrJoinView(new LeaveRoomResponseModel(false, true));
    }
}
