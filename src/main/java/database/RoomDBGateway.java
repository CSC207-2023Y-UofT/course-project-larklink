package database;

import models.RoomDBModel;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBModel> getRooms();
    void addARoom(RoomDBModel request);
    void joinARoom(Integer userID, RoomDBModel request);
    RoomDBModel getARoom(Integer roomID);
    void leaveARoom(RoomDBModel room);
}
