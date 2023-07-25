package leave_room;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBRequestModel> loadRooms();
    void updateRoomActiveUsers(RoomDBRequestModel requestModel);
    void leaveRoom(Integer roomID, Integer userID);
}
