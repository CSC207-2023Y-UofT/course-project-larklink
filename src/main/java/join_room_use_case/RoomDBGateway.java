package join_room_use_case;

import entities.User;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBResponseModel> loadRooms();

    public void updateRoomActiveUsers(User user);
}
