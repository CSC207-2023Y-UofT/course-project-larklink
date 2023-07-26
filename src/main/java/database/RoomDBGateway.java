package database;

import models.RoomDBModel;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBModel> loadRooms();
    void joinRoom(RoomDBModel request, Integer currUserID);
    void leaveRoom(Integer roomId, Integer currUserID);

    RoomDBModel fetchRoom(Integer roomID);

    void saveRoom(RoomDBModel request);
}
