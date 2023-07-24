package leaveRoom;

import java.util.List;

public class RoomDBRequestModel {
    private Integer roomID;
    private List<Integer> activeUsers;
    private String host;

    public RoomDBRequestModel(Integer roomID, List<Integer> activeUsers, String host) {
        this.roomID = roomID;
        this.activeUsers = activeUsers;
        this.host = host;
    }

    public int getRoomId() {
        return roomID;
    }
    public List<Integer> getActiveUsers() {
        return activeUsers;
    }
}
