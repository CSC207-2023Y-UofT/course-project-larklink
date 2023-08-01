package use_cases.join_room;

import use_cases.RoomDBModel;

import java.util.List;

public interface JoinByIDDBGateway {
    List<RoomDBModel> getRooms();
    void updateARoom(RoomDBModel request);
}
