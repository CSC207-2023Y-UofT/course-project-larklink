package join_room;


import models.UserDBModel;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBModel> loadRooms();

    void updateRoomActiveUsers(UserDBModel user);
}
