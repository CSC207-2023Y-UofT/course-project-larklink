package use_cases_and_adapters.leave_room;

import use_cases_and_adapters.RoomDBModel;
/**
 * The LeaveRoomDBGateway interface provides methods to interact with the Room database
 * for updating a room and getting a room based on the roomID.
 */
public interface LeaveRoomDBGateway {
    /**
     * Retrieves a room from the database based on the provided roomID.
     *
     * @param roomID The unique ID of the room to retrieve.
     * @return The RoomDBModel object representing the room with the specified roomID, or null if not found.
     */
    RoomDBModel getARoom(Integer roomID);
    /**
     * Update a room when a user leaves the room.
     *
     * @param room The RoomDBModel object representing the room to leave.
     */
    void updateARoom(RoomDBModel room);
}
