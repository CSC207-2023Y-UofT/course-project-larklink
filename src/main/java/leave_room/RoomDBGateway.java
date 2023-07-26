package leave_room;

import java.util.List;

/**
 * The RoomDBGateway interface defines the contract for accessing room data in the "database".
 * The methods to load rooms, update active users for a particular room, and simulate a user leaving a room must all have
 * implementations in the classes that implement this interface.
 */
public interface RoomDBGateway {

    /**
     * Retrieves the list of active rooms from the "database".
     *
     * @return A list of RoomDBRequestModel containing information about active rooms and their active users.
     */
    List<RoomDBRequestModel> loadRooms();

    /**
     * Updates the active users of a specific room in the "database".
     *
     * @param requestModel The RoomDBRequestModel containing the updated information for the room.
     */
    void updateRoomActiveUsers(RoomDBRequestModel requestModel);

    /**
     * Removes users from the "database"'s list of active users to simulate a user leaving a room.
     *
     * @param roomID The unique id of the room from which the user wants to leave.
     * @param userID The unique id of the user who wants to leave the room.
     */
    void leaveRoom(Integer roomID, Integer userID);
}
