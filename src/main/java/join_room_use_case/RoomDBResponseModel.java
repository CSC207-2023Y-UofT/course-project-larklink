package join_room_use_case;

import entities.Room;

public class RoomDBResponseModel {
    private final Room room;

    public RoomDBResponseModel(Room room) {
        this.room = room;
    }

    public Room getRoom(){
        return room;
    }

}
