package entities;

import java.util.*;

public class Room {
    static private Integer roomID;
    static private String roomName;
    static private Integer hostID;
    static private List<Integer> activeUserIDs;
    static private String messageHistory;

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