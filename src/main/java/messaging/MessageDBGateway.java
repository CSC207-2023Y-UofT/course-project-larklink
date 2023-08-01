package messaging;

import database.RoomDBModel;

public interface MessageDBGateway {
    RoomDBModel getARoom(Integer roomID);
    void updateARoom(RoomDBModel room);
}
