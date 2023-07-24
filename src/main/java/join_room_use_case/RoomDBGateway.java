package join_room_use_case;

import signup_and_login.UserDBModel;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBResponseModel> loadRooms();

    void updateRoomActiveUsers(UserDBModel user);
}
