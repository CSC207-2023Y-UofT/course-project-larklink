package use_cases_and_adapters.host_room;

import use_cases_and_adapters.RoomDBModel;

import java.util.List;

public interface HostRoomDBGateway {
    List<RoomDBModel> getRooms();
    void updateARoom(RoomDBModel request);
}