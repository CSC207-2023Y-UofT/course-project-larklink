package join_room_use_case;


//Need to add other instance variables and getters when merging with other branches
public class RoomDBModel {
    private final String name;
    private final int roomID;

    public RoomDBModel(String name, int roomID) {
        this.name = name;
        this.roomID = roomID;
    }

    public String getName() {
        return this.name;
    }
    public int getRoomID() {
        return this.roomID;
    }

}
