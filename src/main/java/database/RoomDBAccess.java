package database;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.RoomDBModel;

import java.util.ArrayList;
import java.util.List;

public class RoomDBAccess extends DBAccess<RoomDBModel> implements RoomDBGateway {
    public RoomDBAccess(String urlBase) {
        super(urlBase);
    }

    @Override
    public RoomDBModel fetchRoom(Integer roomID) {
        return fetch(roomID);
    }

    @Override
    public void saveRoom(RoomDBModel request) {
        add(request);
    }

    @Override
    public List<RoomDBModel> loadRooms() {
        return loadAll();
    }

    @Override
    public void joinRoom(RoomDBModel request, Integer currUserID) {
        // implement join room
    }

    @Override
    public void leaveRoom(Integer roomId, Integer currUserID) {
        // implement leave room
    }


    @Override
    protected RoomDBModel parseJsonToObject(JsonObject jsonObject) {

        Integer roomID = jsonObject.get("id").getAsInt();
        Integer host = jsonObject.get("host").getAsInt();
        String name = jsonObject.get("name").getAsString();
        List<Integer> activeUsers = new ArrayList<>();

        if (jsonObject.get("activeUsers") != null) {
            JsonArray userList = jsonObject.get("activeUsers").getAsJsonArray();
            for (JsonElement userElement : userList) {
                JsonObject userObject = userElement.getAsJsonObject();
                int userId = userObject.get("id").getAsInt();
                activeUsers.add(userId);
            }
        }
        return new RoomDBModel(roomID, activeUsers, host, name);
    }

    @Override
    protected JsonObject createJsonObjectFromModel(RoomDBModel model) {
        JsonObject roomObject = new JsonObject();
        roomObject.addProperty("host", model.getHost());
        roomObject.addProperty("name", model.getName());

        JsonArray activeUsers = new JsonArray();
        for (Integer userId : model.getActiveUsers()) {
            activeUsers.add(userId);
        }
        roomObject.addProperty("activeUsers", activeUsers.toString());

        JsonObject mainObject = new JsonObject();
        mainObject.add("room", roomObject);

        return mainObject;
    }

    @Override
    protected String getRoute() {
        return "rooms";
    }
}
