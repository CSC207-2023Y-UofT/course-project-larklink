package leave_room;

import java.util.List;

public class RoomDBRequestModel {
    private Integer roomID;
    private List<Integer> activeUsers;

    public RoomDBRequestModel(Integer roomID, List<Integer> activeUsers) {
        this.roomID = roomID;
        this.activeUsers = activeUsers;
    }

    public Integer getRoomId() {
        return roomID;
    }
    public List<Integer> getActiveUsers() {
        return activeUsers;
    }
}
