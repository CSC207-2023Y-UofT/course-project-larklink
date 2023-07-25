package database;

import com.google.gson.*;
import models.RoomDBModel;
import util.RemoteUtilities;

import java.util.ArrayList;
import java.util.List;

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
                RemoteUtilities remoteUtilities = new RemoteUtilities(urlbase);

                rooms.add(remoteUtilities.ConvertToRoomDB(roomObject, jsonObject, roomID));
            }

            return rooms;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public RoomDBModel fetchRoom(Integer roomID) {

        try {
            String result = performHttpRequest("GET", null, roomID);

            // Parse JSON data
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            JsonObject roomObject = jsonObject.get("room").getAsJsonObject();
            RemoteUtilities remoteUtilities = new RemoteUtilities(urlbase);

            return remoteUtilities.ConvertToRoomDB(roomObject, jsonObject, roomID);

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

            roomObject.addProperty("name", request.getName());

            JsonObject mainObject = new JsonObject();
            mainObject.add("room", roomObject);

            String jsonInputString = mainObject.toString();
            performHttpRequest("POST", jsonInputString, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void joinRoom(RoomDBModel roomDBModel, Integer currUserID) {
        // TODO: implement
    }

    @Override
    public void leaveRoom(Integer roomId, Integer currUserId) {
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
