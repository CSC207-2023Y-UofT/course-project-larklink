package messaging;

import database.RoomDBModel;
/**
 * The MessageDBGateway interface defines methods to interact with the database related to rooms and messages.
 */
public interface MessageDBGateway {
    /**
     * Retrieves a RoomDBModel object for the given room ID from the database.
     *
     * @param roomID The ID of the room to retrieve from the database.
     * @return The RoomDBModel object representing the room retrieved from the database.
     */
    RoomDBModel getARoom(Integer roomID);

    /**
     * Sends a RoomDBModel object representing a room to the database.
     *
     * @param room The RoomDBModel object representing the room to be sent to the database.
     */
    void sendAMessage(RoomDBModel room);
}
