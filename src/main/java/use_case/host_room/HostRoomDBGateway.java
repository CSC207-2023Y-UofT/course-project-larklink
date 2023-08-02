package use_case.host_room;

import database.RoomDBModel;

import java.util.List;

public interface HostRoomDBGateway {
    List<RoomDBModel> getRooms();
    void addARoom(RoomDBModel request);
}