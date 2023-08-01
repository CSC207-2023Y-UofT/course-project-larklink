package leave_room;

import database.RoomDBModel;

/**
 * The LeaveRoomDBGateway interface provides methods to interact with the Room database
 * for leaving a room and getting a room based on the roomID.
 */
public interface LeaveRoomDBGateway {

    /**
     * Allows a user to leave a room.
     *
     * @param room The RoomDBModel object representing the room to leave.
     */
    void leaveARoom(RoomDBModel room);

    /**
     * Retrieves a room from the database based on the provided roomID.
     *
     * @param roomID The unique ID of the room to retrieve.
     * @return The RoomDBModel object representing the room with the specified roomID, or null if not found.
     */
    RoomDBModel getARoom(Integer roomID);
}
