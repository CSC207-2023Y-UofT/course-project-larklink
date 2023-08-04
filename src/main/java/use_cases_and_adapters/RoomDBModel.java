package use_cases_and_adapters;

import java.util.List;

/**
 * This class encapsulates room data from database.
 */
public class RoomDBModel {
    private final int roomID;
    private final String roomName;
    private final Integer hostID;
    private List<Integer> activeUserIDs;
    private String messageHistory;

    /**
     * Constructs a RoomDBModel object.
     *
     * @param roomID The unique identifier of the room
     * @param roomName The name of the room
     * @param hostID The unique identifier of the room's host
     * @param activeUserIDs The list of users' unique identifiers of the room
     * @param messageHistory The message history of the room
     */
    public RoomDBModel(int roomID, String roomName, Integer hostID, List<Integer> activeUserIDs, String messageHistory) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.hostID = hostID;
        this.activeUserIDs = activeUserIDs;
        this.messageHistory = messageHistory;
    }

    /**
     * A getter for unique identifier of the room.
     *
     * @return The unique identifier of the room.
     */
    public int getRoomID() {
        return this.roomID;
    }

    /**
     * A getter for name of the room.
     *
     * @return The name of the room.
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * A getter for unique identifier of the room's host.
     *
     * @return The unique identifier of the room's host.
     */
    public Integer getHostID() {
        return this.hostID;
    }

    /**
     * A getter for a list of users' unique identifiers of the room.
     *
     * @return The list of users' unique identifiers of the room.
     */
    public List<Integer> getActiveUserIDs() {
        return this.activeUserIDs;
    }

    /**
     * A getter for message history of the room.
     *
     * @return The message history of the room.
     */
    public String getMessageHistory() {
        return this.messageHistory;
    }

    /**
     * Sets activeUserIDs of this room as given input
     *
     * @param activeUserIDs updated active userID list
     */
    public void setActiveUserIDs(List<Integer> activeUserIDs) {
        this.activeUserIDs = activeUserIDs;
    }

    /**
     * Sets message history of this room as given input
     *
     * @param messageHistory updated message history
     */
    public void setMessageHistory(String messageHistory) {
        this.messageHistory = messageHistory;
    }
}
