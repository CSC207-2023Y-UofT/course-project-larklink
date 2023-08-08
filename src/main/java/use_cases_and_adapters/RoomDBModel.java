package use_cases_and_adapters;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates room data from database.
 */
public class RoomDBModel {
    private final int id;
    private final String roomName;
    private final Integer hostId;
    private String activeUsers;
    private String messageHistory;

    /**
     * The constructor for the RoomDBModel
     *
     * @param id The unique identifier of the room
     * @param roomName The name of the room
     * @param hostId The unique identifier of the room's host
     * @param activeUsers The string depiction of the users' currently inside the room
     * @param messageHistory The message history of the room
     */
    public RoomDBModel(int id, String roomName, Integer hostId, String activeUsers, String messageHistory) {
        this.id = id;
        this.roomName = roomName;
        this.hostId = hostId;
        this.activeUsers = activeUsers;
        this.messageHistory = messageHistory;
    }

    /**
     * A getter for the unique identifier of the room.
     *
     * @return The unique identifier of the room.
     */
    public int getRoomID() {
        return this.id;
    }

    /**
     * A getter for the name of the room.
     *
     * @return The name of the room.
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * A getter for the unique identifier of the room's host.
     *
     * @return The unique identifier of the room's host.
     */
    public Integer getHostID() {
        return this.hostId;
    }

    /**
     * A getter for the list of users' unique identifiers of the room.
     * This method converts it from a string since our database needs a string.
     *
     * @return The list of users' unique identifiers of the room.
     */
    public List<Integer> getActiveUserIDs() {
        List<Integer> list = new ArrayList<>();
        if (!(activeUsers == null || activeUsers.trim().isEmpty())) {
            for (String userId : activeUsers.split(",")) {
                list.add(Integer.parseInt(userId.trim()));
            }
        }
        return list;
    }

    /**
     * A getter for the message history of the room.
     *
     * @return The message history of the room.
     */
    public String getMessageHistory() {
        return this.messageHistory;
    }

    /**
     * Sets activeUserIDs of the room as given input
     * This method converts to a string since our database needs a string.
     *
     * @param activeUserIDs the active userID list
     */
    public void setActiveUserIDs(List<Integer> activeUserIDs) {
        StringBuilder sb = new StringBuilder();
        if (!(activeUserIDs == null || activeUserIDs.isEmpty())) {
            for (Integer userId : activeUserIDs) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(userId);
            }
        }
        this.activeUsers = sb.toString();
    }

    /**
     * Sets message history of the room as given input
     *
     * @param messageHistory the message history
     */
    public void setMessageHistory(String messageHistory) {
        this.messageHistory = messageHistory;
    }
}
