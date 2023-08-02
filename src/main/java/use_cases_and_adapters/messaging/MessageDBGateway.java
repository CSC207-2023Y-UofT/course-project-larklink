package use_cases_and_adapters.messaging;

import use_cases_and_adapters.RoomDBModel;

public interface MessageDBGateway {
    RoomDBModel getARoom(Integer roomID);
    void updateARoom(RoomDBModel room);
}
