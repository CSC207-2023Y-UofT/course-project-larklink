package use_cases.leave_room;

import use_cases.RoomDBModel;

public interface LeaveRoomDBGateway {
    RoomDBModel getARoom(Integer roomID);
    void updateARoom(RoomDBModel room);
}
