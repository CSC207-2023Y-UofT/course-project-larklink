package use_cases.messaging;

import use_cases.RoomDBModel;

public interface MessageDBGateway {
    RoomDBModel getARoom(Integer roomID);
    void updateARoom(RoomDBModel room);
}
