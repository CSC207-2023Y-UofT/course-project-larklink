package leave_room;

import java.util.List;

public class LeaveRoomInteractor implements LeaveRoomInputBoundary {
    private RoomDBGateway roomDBGateway;
    private LeaveRoomOutputBoundary leaveRoomOutputBoundary;

    public LeaveRoomInteractor(RoomDBGateway roomDBGateway, LeaveRoomOutputBoundary leaveRoomOutputBoundary) {
        this.roomDBGateway = roomDBGateway;
        this.leaveRoomOutputBoundary = leaveRoomOutputBoundary;
    }

    @Override
    public void leaveRoom(String roomID, String userID) {
        List<RoomDBRequestModel> rooms = roomDBGateway.loadRooms();

        for (RoomDBRequestModel room : rooms) {
            if (room.getRoomId().equals(roomID)) {
                List<String> activeUsers = room.getActiveUsers();
                if (activeUsers.contains(userID)) {
                    activeUsers.remove(userID);
                    roomDBGateway.updateRoomActiveUsers(room);
                    leaveRoomOutputBoundary.prepareHostOrJoinView(new LeaveRoomResponseModel(true, false));
                }
            }
        }

        leaveRoomOutputBoundary.prepareHostOrJoinView(new LeaveRoomResponseModel(false, true));
    }
}
