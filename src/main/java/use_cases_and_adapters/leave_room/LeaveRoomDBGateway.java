package use_cases_and_adapters.leave_room;

import use_cases_and_adapters.RoomDBModel;

public interface LeaveRoomDBGateway {
    RoomDBModel getARoom(Integer roomID);
    void updateARoom(RoomDBModel room);
}
