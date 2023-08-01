package entities;

import java.util.*;

/**
 * This Room class represents a chat room in the system.
 * It employs a Singleton-like pattern where all its members are static, which means
 * there is effectively only one room in the entire system at a given time.
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
    public static void setRoom(Integer roomID, String roomName, Integer hostID, List<Integer> activeUserIDs, String messageHistory) {
        Room.roomID = roomID;
        Room.roomName = roomName;
        Room.hostID = hostID;
        Room.activeUserIDs = activeUserIDs;
        Room.messageHistory = messageHistory;
    }

    public static Integer getRoomID() {
        return Room.roomID;
    }

    public static String getRoomName() {
        return Room.roomName;
    }

    public static Integer getHostID() {
        return Room.hostID;
    }

    public static List<Integer> getActiveUserIDs() {
        return Room.activeUserIDs;
    }

    public static String getMessageHistory() {
        return Room.messageHistory;
    }
}