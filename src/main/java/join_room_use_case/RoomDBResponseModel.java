package join_room_use_case;

import entity.Room;

public class RoomDBResponseModel {
    private Room room;

    public RoomDBResponseModel(Room room) {
        this.room = room;
    }

    public Room getRoom(){
        return room;
    }

}
