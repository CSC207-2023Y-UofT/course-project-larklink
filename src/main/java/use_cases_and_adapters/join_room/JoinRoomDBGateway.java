package use_cases_and_adapters.join_room;
import use_cases_and_adapters.RoomDBModel;
import java.util.List;

/**
 * A DB gateway for join room use case.
 * This is an abstraction layer between JoinRoomInteractor and RoomDBAccess.
 */
public interface JoinRoomDBGateway {

    /**
     * Retrieves a list of all rooms from the database.
     *
     * @return a List of RoomDBModel objects representing all rooms in the database,
     * or an empty list if an error occurred.
     */
    List<RoomDBModel> getRooms();

    /**
     * Updates a single room in the database.
     *
     * @param request a RoomDBModel object containing the updated data for the room.
     */
    void updateARoom(RoomDBModel request);
}
