package use_cases_and_adapters.leave_room;

import use_cases_and_adapters.RoomDBModel;

public interface LeaveRoomDBGateway {
    void leaveARoom(RoomDBModel room);
    RoomDBModel getARoom(Integer roomID);
}
