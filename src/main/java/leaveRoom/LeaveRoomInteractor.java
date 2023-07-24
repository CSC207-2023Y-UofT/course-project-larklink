package leaveRoom;

import java.util.List;

public class LeaveRoomInteractor implements LeaveRoomInputBoundary {
    private RoomDBGateway roomDBGateway;
    private LeaveRoomOutputBoundary leaveRoomOutputBoundary;

    public LeaveRoomInteractor(RoomDBGateway roomDBGateway, LeaveRoomOutputBoundary leaveRoomOutputBoundary) {
        this.roomDBGateway = roomDBGateway;
        this.leaveRoomOutputBoundary = leaveRoomOutputBoundary;
    }

    @Override
    public void leaveRoom(String roomId, String currUserId) {
        List<RoomDBRequestModel> rooms = roomDBGateway.loadRooms();

        for (RoomDBRequestModel room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                List<String> activeUsers = room.getActiveUsers();
                if (activeUsers.contains(currUserId)) {
                    activeUsers.remove(currUserId);
                    roomDBGateway.updateRoomActiveUsers(room);
                    leaveRoomOutputBoundary.prepareHostOrJoinView(new LeaveRoomResponseModel(true, false));
                }
            }
        }

        leaveRoomOutputBoundary.prepareHostOrJoinView(new LeaveRoomResponseModel(false, true));
    }
}
