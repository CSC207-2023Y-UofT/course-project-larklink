package database;

import models.RoomDBModel;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBModel> retrieveEveryRoom();
    void addARoom(RoomDBModel request);
    void joinARoom(RoomDBModel request, Integer currUserID);
    void leaveARoom(Integer roomId, Integer currUserID);
}
