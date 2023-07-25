package leave_room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDBAccess implements RoomDBGateway {
    private Map<Integer, List<Integer>> activeRooms = new HashMap<>();

//    public RoomDBAccess() {
//        createRooms();
//    }
//
//    private void createRooms() {
//        List<String> activeUsers1 = new ArrayList<>();
//        activeUsers1.add("user1");
//        activeUsers1.add("user2");
//        activeRooms.put("room1", activeUsers1);
//        List<String> activeUsers2 = new ArrayList<>();
//        activeUsers2.add("user3");
//        activeUsers2.add("user4");
//        activeRooms.put("room2", activeUsers2);
//    }


    @Override
    public List<RoomDBRequestModel> loadRooms() {
        List<RoomDBRequestModel> rooms = new ArrayList<>();
        for (Integer roomID : activeRooms.keySet()) {
            List<Integer> activeUsers = activeRooms.get(roomID);
            RoomDBRequestModel room = new RoomDBRequestModel(roomID, activeUsers);
            rooms.add(room);
        }
        return rooms;
    }

    @Override
    public void updateRoomActiveUsers(RoomDBRequestModel requestModel) {
        Integer roomID = requestModel.getRoomId();
        List<Integer> activeUsers = requestModel.getActiveUsers();
        activeRooms.put(roomID, activeUsers);
    }

    @Override
    public void leaveRoom(Integer roomID, Integer userID) {
        if (activeRooms.containsKey(roomID)) {
            List<Integer> activeUsers = activeRooms.get(roomID);
            if (activeUsers.contains(userID)) {
                activeUsers.remove(userID);
            }
        }
    }
}
