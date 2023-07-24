package leaveRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDBAccess implements RoomDBGateway {
    private Map<String, List<String>> activeRooms = new HashMap<>();

    public RoomDBAccess() {
        createRooms();
    }

    private void createRooms() {
        List<String> activeUsers1 = new ArrayList<>();
        activeUsers1.add("user1");
        activeUsers1.add("user2");
        activeRooms.put("room1", activeUsers1);
        List<String> activeUsers2 = new ArrayList<>();
        activeUsers2.add("user3");
        activeUsers2.add("user4");
        activeRooms.put("room2", activeUsers2);
    }


    @Override
    public List<RoomDBRequestModel> loadRooms() {
        List<RoomDBRequestModel> rooms = new ArrayList<>();
        for (String roomId : activeRooms.keySet()) {
            List<String> activeUsers = activeRooms.get(roomId);
            RoomDBRequestModel room = new RoomDBRequestModel(roomId, activeUsers);
            rooms.add(room);
        }
        return rooms;
    }

    @Override
    public void updateRoomActiveUsers(RoomDBRequestModel requestModel) {
        String roomId = requestModel.getRoomId();
        List<String> activeUsers = requestModel.getActiveUsers();
        activeRooms.put(roomId, activeUsers);
    }

    @Override
    public void leaveRoom(String roomId, String currUserId) {
        if (activeRooms.containsKey(roomId)) {
            List<String> activeUsers = activeRooms.get(roomId);
            if (activeUsers.contains(currUserId)) {
                activeUsers.remove(currUserId);
            }
        }
    }
}
