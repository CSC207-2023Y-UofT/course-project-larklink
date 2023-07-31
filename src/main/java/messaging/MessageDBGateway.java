package messaging;

import database.RoomDBModel;

public interface MessageDBGateway {
    RoomDBModel getARoom(Integer roomID);
    void sendAMessage(RoomDBModel room);
}
