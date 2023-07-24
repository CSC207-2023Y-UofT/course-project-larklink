package leaveRoom;

import java.util.List;

public class RoomDBModel {
    private final int roomID;
    // stored using userIDs
    private List<Integer> activeUsers;
    private Integer host;

    public RoomDBModel(int roomID, List<Integer> activeUsers, Integer host) {
        this.roomID = roomID;
        this.activeUsers = activeUsers;
        this.host = host;
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

}
