package entities;

import java.util.*;
import java.util.ArrayList;

public class Room {
    private String name;
    private User host;
    private List<User> activeUsers;
    private List<Message> messageHistory;

    public Room(String name, User host) {
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

    public String getName() {
        return name;
    }
    public User getHost() {
        return host;
    }
}
