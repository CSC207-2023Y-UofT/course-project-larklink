package Send_Message.Databases;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class RoomMessageHistoryDBAccess implements Serializable{
    private static List<Object> chatData;
    public RoomMessageHistoryDBAccess(List<Object> rooms){
        chatData = rooms;
    }
    public Object findByID(String RoomID) {
        Object roomFound = null;
        for(Object room : chatData){
            if (((ChatRoomEnt)room).getId().equals(rid)){
                roomFound = room;
            }
        }
        return roomFound;
    }
}
