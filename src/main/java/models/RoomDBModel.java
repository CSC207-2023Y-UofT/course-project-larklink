package models;

import java.util.List;

public class RoomDBModel {
    private final int roomID;
    // stored using userIDs
    private List<Integer> activeUsers;
    private final Integer host;
    private final String name;
    private String messageHistory;


    public RoomDBModel(int roomID, List<Integer> activeUsers, Integer host, String name, String messageHistory) {
        this.roomID = roomID;
        this.activeUsers = activeUsers;
        this.host = host;
        this.name = name;
        this.messageHistory = messageHistory;
    }

    public int getRoomID() {
        return this.roomID;
    }
    public List<Integer> getActiveUsers() {
        return this.activeUsers;
    }
    public Integer getHost() {
        return this.host;
    }

    public String getName() { return this.name; }
    public String getMessageHistory() { return this.messageHistory; }


    public void setActiveUsers(List<Integer> activeUsers) {
        this.activeUsers = activeUsers;
    }

    public void setMessageHistory(String messageHistory) {
        this.messageHistory = messageHistory;
    }

}