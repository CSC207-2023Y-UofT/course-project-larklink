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
    public void leaveRoom(Integer roomID, Integer userID) {
        List<RoomDBRequestModel> rooms = roomDBGateway.loadRooms();

        for (RoomDBRequestModel room : rooms) {
            if (room.getRoomId().equals(roomID)) {
                List<Integer> activeUsers = room.getActiveUsers();
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
