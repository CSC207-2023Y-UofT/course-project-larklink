package entities;

import java.util.*;
import java.util.ArrayList;

public class Room {
    private final Integer roomID;
    private final String name;
    private final User host;
    private List<User> activeUsers;
    private List<Message> messageHistory;

    public Room(Integer roomID, String name, User host) {
        this.roomID = roomID;
        this.name = name;
        this.host = host;
        this.activeUsers = new ArrayList<User>();
        this.messageHistory = new ArrayList<Message>();

        activeUsers.add(host);
    }

    public void addUser(User person) {
        activeUsers.add(person);
    }
    public void removeUser(User person) {
        activeUsers.remove(person);
    }

    public Integer getRoomID() { return this.roomID; }
    public String getName() {
        return this.name;
    }
    public User getHost() {
        return this.host;
    }
}
