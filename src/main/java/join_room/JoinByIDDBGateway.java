package join_room;

import database.RoomDBModel;

import java.util.List;

public interface JoinByIDDBGateway {
    List<RoomDBModel> getRooms();
    void joinARoom(RoomDBModel request);
}
