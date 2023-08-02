package join_room;

import database.RoomDBModel;

import java.util.List;

/**
 * A DB gateway for join room use case.
 * This is an abstraction layer between JoinRoominteractor and RoomDBAccess.
 */
public interface JoinRoomDBGateway {
    List<RoomDBModel> getRooms();
    void joinARoom(Integer userID, RoomDBModel request);
}
