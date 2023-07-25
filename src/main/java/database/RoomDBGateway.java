package database;

import models.RoomDBModel;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBModel> loadRooms();
    void joinRoom(RoomDBModel requestModel, String currUserID);
    void leaveRoom(String roomId, String currUserID);

    RoomDBModel fetchRoom(int roomID);

    void saveRoom(RoomDBModel request);
}
