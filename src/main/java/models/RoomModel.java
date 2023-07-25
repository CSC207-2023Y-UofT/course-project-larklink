package models;

import java.util.List;

public class RoomModel {
    private final List<Integer> activeUsers;
    private final Integer host;

    private final String name;

    public RoomModel(List<Integer> activeUsers, Integer host, String name) {
        this.host = host;
        this.activeUsers = activeUsers;
        this.name = name;
    }

    public List<Integer> getActiveUsers() {
        return activeUsers;
    }

    public Integer getHost() {
        return host;
    }

    public String getName() { return this.name; }
}
