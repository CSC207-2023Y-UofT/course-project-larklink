package database;

import models.RoomDBModel;

import java.util.List;

public interface RoomDBGateway {
    List<RoomDBModel> retrieveEveryRoom();
    void joinARoom(RoomDBModel request, Integer currUserID);
    void leaveARoom(Integer roomId, Integer currUserID);

    RoomDBModel retrieveARoom(Integer roomID);

    void addARoom(RoomDBModel request);
}
