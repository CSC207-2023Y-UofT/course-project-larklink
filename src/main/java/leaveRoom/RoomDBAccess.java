package leaveRoom;

import com.google.gson.*;
import entities.Message;
import entities.Room;
import entities.User;
import signup_and_login.UserDBModel;
import util.RemoteUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDBAccess implements RoomDBGateway {
    final private String urlbase;

    public RoomDBAccess(String urlbase) {
        this.urlbase = urlbase;
    }

    @Override
    public List<RoomDBModel> loadRooms() {
        try {
            String result = performHttpRequest("GET", null, null);
            // Parse JSON data
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            JsonArray roomsArray = jsonObject.get("rooms").getAsJsonArray();
            List<RoomDBModel> rooms = new ArrayList<>();

            for (JsonElement roomElement : roomsArray) {
                JsonObject roomObject = roomElement.getAsJsonObject();
                int roomID = roomObject.get("id").getAsInt();
                Integer host = roomObject.get("host").getAsInt();
                JsonArray activeUsers = roomObject.get("activeUsers").getAsJsonArray();

                List<Integer> users = new ArrayList<>();
                for (JsonElement userElement : activeUsers) {
                    JsonObject userObject = userElement.getAsJsonObject();
                    int userId = userObject.get("id").getAsInt();
                    users.add(userId);
                }
                rooms.add(new RoomDBModel(roomID, users, host));
            }

            return rooms;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public RoomDBModel fetchRoom(int roomID) {

        try {
            String result = performHttpRequest("GET", null, roomID);

            // Parse JSON data
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            JsonObject roomObject = jsonObject.get("room").getAsJsonObject();

            Integer host = roomObject.get("host").getAsInt();

            JsonArray userList = jsonObject.get("activeUsers").getAsJsonArray();

            List<Integer> users = new ArrayList<>();
            for (JsonElement userElement : userList) {
                JsonObject userObject = userElement.getAsJsonObject();
                int userId = userObject.get("id").getAsInt();
                users.add(userId);
            }

            return new RoomDBModel(roomID, users, host);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveRoom(RoomDBModel request) {
        try {
            JsonObject roomObject = new JsonObject();
            JsonArray users = new JsonArray();
            for (Integer userID : request.getActiveUsers()) {
                users.add(new JsonPrimitive(userID));
            }

            roomObject.addProperty("host", request.getHost());
            roomObject.addProperty("activeUsers", users.toString());

            JsonObject mainObject = new JsonObject();
            mainObject.add("room", roomObject);

            String jsonInputString = mainObject.toString();
            performHttpRequest("POST", jsonInputString, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void joinRoom(RoomDBModel roomDBModel, String currUserID) {
        // TODO: implement
    }

    @Override
    public void leaveRoom(String roomId, String currUserId) {
        // TODO: Needs to find which room user is in and remove user
        //if (activeRooms.containsKey(roomId)) {
        //    List<Integer> activeUsers = activeRooms.get(roomId);
        //    if (activeUsers.contains(currUserId)) {
        //        activeUsers.remove(currUserId);
        //    }
        // }
    }

    private String performHttpRequest(String method, String jsonInputString, Integer id) throws Exception {
        RemoteUtilities remote_util = new RemoteUtilities(urlbase);
        return remote_util.performHttpRequest(method, jsonInputString, id, "rooms");
    }
}
