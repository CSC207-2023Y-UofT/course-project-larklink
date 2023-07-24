package leaveRoom;

import java.util.List;

public class RoomDBRequestModel {
    private String roomId;
    private List<String> activeUsers;

    public RoomDBRequestModel(String roomId, List<String> activeUsers) {
        this.roomId = roomId;
        this.activeUsers = activeUsers;
    }

    public String getRoomId() {
        return roomId;
    }
    public List<String> getActiveUsers() {
        return activeUsers;
    }
}
