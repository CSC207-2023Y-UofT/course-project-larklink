package leave_room;

import java.util.List;

public class RoomDBRequestModel {
    private String roomID;
    private List<String> activeUsers;

    public RoomDBRequestModel(String roomID, List<String> activeUsers) {
        this.roomID = roomID;
        this.activeUsers = activeUsers;
    }

    public String getRoomId() {
        return roomID;
    }
    public List<String> getActiveUsers() {
        return activeUsers;
    }
}
