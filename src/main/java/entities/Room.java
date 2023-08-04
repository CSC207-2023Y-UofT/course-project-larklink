package entities;

import java.util.*;

/**
 * This class represents a chat room and employs a Singleton-like pattern where all its members are static, since the
 * logged-in user can only be in one room at any given time. This design pattern allows other use cases to access room
 * details easily.
 */
public class Room {
    private static Integer roomID;
    private static String roomName;
    private static Integer hostID;
    private static List<Integer> activeUserIDs;
    private static String messageHistory;

    /**
     * Set the room details.
     * @param roomID The unique identifier for the room.
     * @param roomName The name of the room.
     * @param hostID The unique identifier for the host of the room.
     * @param activeUserIDs The list of active user IDs in the room.
     * @param messageHistory The message history of the room.
     */
    public static void setRoom(Integer roomID, String roomName, Integer hostID, List<Integer> activeUserIDs,
                               String messageHistory) {
        Room.roomID = roomID;
        Room.roomName = roomName;
        Room.hostID = hostID;
        Room.activeUserIDs = activeUserIDs;
        Room.messageHistory = messageHistory;
    }

    /**
     * A getter for the unique identifier of the room.
     *
     * @return The unique identifier of the room.
     */
    public static Integer getRoomID() {
        return Room.roomID;
    }

    /**
     * A getter for the name of the room.
     *
     * @return The name of the room.
     */
    public static String getRoomName() {
        return Room.roomName;
    }

    /**
     * A getter for the unique identifier for the host of the room.
     *
     * @return The unique identifier for the host of the room.
     */
    public static Integer getHostID() {
        return Room.hostID;
    }

    /**
     * A getter for the list of active user unique identifiers of the room.
     *
     * @return The list of active user unique identifiers of the room.
     */
    public static List<Integer> getActiveUserIDs() {
        return Room.activeUserIDs;
    }

    /**
     * A getter for the message history of the room.
     *
     * @return The message history of the room.
     */
    public static String getMessageHistory() {
        return Room.messageHistory;
    }
}