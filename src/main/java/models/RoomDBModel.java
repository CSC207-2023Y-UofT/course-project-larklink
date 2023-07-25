package models;

import java.util.List;

public class RoomDBModel {
    private final int roomID;
    // stored using userIDs
    private List<Integer> activeUsers;
    private final Integer host;
    private final String name;

    public RoomDBModel(int roomID, List<Integer> activeUsers, Integer host, String name) {
        this.roomID = roomID;
        this.activeUsers = activeUsers;
        this.host = host;
        this.name = name;
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

}
