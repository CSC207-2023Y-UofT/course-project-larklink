package use_cases_and_adapters.join_room;
import use_cases_and_adapters.RoomDBModel;
import java.util.List;

public interface JoinRoomDBGateway {
    List<RoomDBModel> getRooms();
    void updateARoom(RoomDBModel request);
}
