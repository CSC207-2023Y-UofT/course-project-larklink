package use_cases_and_adapters.join_room;
import use_cases_and_adapters.RoomDBModel;
import java.util.List;

/**
 * A DB gateway for join room use case.
 * This is an abstraction layer between JoinRoomInteractor and RoomDBAccess.
 */
public interface JoinRoomDBGateway {
    List<RoomDBModel> getRooms();
    void updateARoom(RoomDBModel request);
}
