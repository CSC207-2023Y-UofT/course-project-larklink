package use_case.join_room;

import database.RoomDBModel;

import java.util.List;

public interface JoinRoomDBGateway {
    List<RoomDBModel> getRooms();
    void joinARoom(Integer userID, RoomDBModel request);
}
