package leave_room;

import database.RoomDBModel;

public interface LeaveRoomDBGateway {
    void leaveARoom(RoomDBModel room);
    RoomDBModel getARoom(Integer roomID);
}
