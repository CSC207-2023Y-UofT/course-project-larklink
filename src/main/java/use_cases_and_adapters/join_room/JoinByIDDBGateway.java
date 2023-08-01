package use_cases_and_adapters.join_room;

import use_cases_and_adapters.RoomDBModel;

import java.util.List;

public interface JoinByIDDBGateway {
    List<RoomDBModel> getRooms();
    void updateARoom(RoomDBModel request);
}
