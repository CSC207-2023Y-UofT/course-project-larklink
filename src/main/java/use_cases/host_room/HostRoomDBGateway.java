package use_cases.host_room;

import use_cases.RoomDBModel;

import java.util.List;

public interface HostRoomDBGateway {
    List<RoomDBModel> getRooms();
    void updateARoom(RoomDBModel request);
}