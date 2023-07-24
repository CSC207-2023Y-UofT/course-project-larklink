package leaveRoom;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBRequestModel> loadRooms();
    void updateRoomActiveUsers(RoomDBRequestModel requestModel);
    void leaveRoom(String roomId, String currUserId);
}
