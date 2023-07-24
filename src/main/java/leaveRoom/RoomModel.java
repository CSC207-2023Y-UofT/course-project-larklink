package leaveRoom;

import java.util.List;

public class RoomModel {
    private final List<Integer> activeUsers;
    private final Integer host;

    public RoomModel(List<Integer> activeUsers, Integer host) {
        this.host = host;
        this.activeUsers = activeUsers;
    }

    public List<Integer> getActiveUsers() {
        return activeUsers;
    }

    public Integer getHost() {
        return host;
    }
}
