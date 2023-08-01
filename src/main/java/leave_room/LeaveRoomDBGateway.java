package leave_room;

import database.RoomDBModel;

public interface LeaveRoomDBGateway {
    RoomDBModel getARoom(Integer roomID);
    void leaveARoom(RoomDBModel room);
}
