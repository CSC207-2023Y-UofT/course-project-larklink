package leave_room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In order to offer access to room data, the RoomDBAccess class implements the RoomDBGateway interface.
 * For storing and retrieving active rooms and their active users, it simulates database access.
 */
public class RoomDBAccess implements RoomDBGateway {
    private Map<Integer, List<Integer>> activeRooms = new HashMap<>();

    /**
     * Retrieves the list of active rooms from the "database".
     *
     * @return A list of RoomDBRequestModel containing information about active rooms and their active users.
     */
    @Override
    public List<RoomDBRequestModel> loadRooms() {
        List<RoomDBRequestModel> rooms = new ArrayList<>();
        for (Integer roomID : activeRooms.keySet()) {
            List<Integer> activeUsers = activeRooms.get(roomID);
            RoomDBRequestModel room = new RoomDBRequestModel(roomID, activeUsers);
            rooms.add(room);
        }
        return rooms;
    }

    /**
     * Updates the active users of a specific room in the "database".
     *
     * @param requestModel The RoomDBRequestModel containing the updated information for the room.
     */
    @Override
    public void updateRoomActiveUsers(RoomDBRequestModel requestModel) {
        Integer roomID = requestModel.getRoomId();
        List<Integer> activeUsers = requestModel.getActiveUsers();
        activeRooms.put(roomID, activeUsers);
    }

    /**
     * Removes users from the "database"'s list of active users to simulate a user leaving a room.
     *
     * @param roomID The unique id of the room from which the user wants to leave.
     * @param userID The unique id of the user who wants to leave the room.
     */
    @Override
    public void leaveRoom(Integer roomID, Integer userID) {
        if (activeRooms.containsKey(roomID)) {
            List<Integer> activeUsers = activeRooms.get(roomID);
            if (activeUsers.contains(userID)) {
                activeUsers.remove(userID);
            }
        }
    }
}
