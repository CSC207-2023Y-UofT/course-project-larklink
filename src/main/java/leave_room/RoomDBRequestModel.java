package leave_room;

import java.util.List;

/**
 * Room data in the "database" is represented by the RoomDBRequestModel class as a request model.
 * It contains details about a room's list of active users and its special roomID.
 */
public class RoomDBRequestModel {
    private Integer roomID;
    private List<Integer> activeUsers;

    /**
     * Constructs a new RoomDBRequestModel with the specified roomID and list of active users.
     *
     * @param roomID      The unique id of the room.
     * @param activeUsers The list of active users in the room.
     */
    public RoomDBRequestModel(Integer roomID, List<Integer> activeUsers) {
        this.roomID = roomID;
        this.activeUsers = activeUsers;
    }

    /**
     * Gets the unique id of the room.
     *
     * @return The room's unique identifier.
     */
    public Integer getRoomId() {
        return roomID;
    }

    /**
     * Gets the list of active users in the room.
     *
     * @return The list of active users.
     */
    public List<Integer> getActiveUsers() {
        return activeUsers;
    }
}
