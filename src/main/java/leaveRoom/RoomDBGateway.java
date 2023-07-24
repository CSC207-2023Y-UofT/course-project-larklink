package leaveRoom;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBModel> loadRooms();
    void updateRoomActiveUsers(RoomDBModel requestModel);
    void leaveRoom(String roomId, String currUserId);

    RoomDBModel fetchRoom(int roomID);

    void saveRoom(RoomDBModel request);
}
