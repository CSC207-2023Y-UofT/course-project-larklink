package entity;

import java.util.*;
import java.util.ArrayList;

public class Room {
    public String name;
    public User host;
    private List<User> ActiveUsers;

    public Room(String name, User host) {
        this.name = name;
        this.host = host;
        this.ActiveUsers = new ArrayList<User>();

        ActiveUsers.add(host);
    }

    public void addUser(User person) {
        ActiveUsers.add(person);
    }
    public void removeUser(User person) {
        ActiveUsers.remove(person);
    }

    public String getName() {
        return name;
    }
    public User getHost() {
        return host;
    }
}
