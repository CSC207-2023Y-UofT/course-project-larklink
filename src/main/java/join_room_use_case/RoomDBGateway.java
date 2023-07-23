package join_room_use_case;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBResponseModel> loadRooms();

    void updateRoomActiveUsers(UserDBModel user);
}
