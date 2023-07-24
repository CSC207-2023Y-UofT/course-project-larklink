package host_room;

import entities.User;

public interface HostRoomOutputBoundary {
    void prepareRoomView(int userID);
    void prepareRoomNameAlreadyTaken();
}
