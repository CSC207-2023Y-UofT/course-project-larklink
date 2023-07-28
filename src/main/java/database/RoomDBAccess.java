package database;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.RoomDBModel;

import java.util.ArrayList;
import java.util.List;

public class RoomDBAccess extends DBAccess<RoomDBModel> implements RoomDBGateway {
    public RoomDBAccess(String urlBase) {
        super(urlBase);
    }

    @Override
    public List<RoomDBModel> getRooms() {
        return getRows();
    }

    @Override
    public RoomDBModel getARoom(Integer roomID) {
        return retrieveARow(roomID);
    }

    @Override
    public void addARoom(RoomDBModel request) {
        addARow(request.getRoomID(), request);
    }

    @Override
    public void joinARoom(Integer userID, RoomDBModel request) {
        modifyARow(request.getRoomID(), request);
    }

    @Override
    public void leaveARoom(RoomDBModel room) {
        modifyARow(room.getRoomID(), room);
    }

    @Override
    protected RoomDBModel jsonToObject(JsonObject jsonObject) {

        // when we fetch one row we get "room: <information here>" so here we "skip" it
        if (jsonObject.has("room")) {
            jsonObject = jsonObject.get("room").getAsJsonObject();
        }

        int roomID = jsonObject.get("id").getAsInt();
        Integer host = jsonObject.get("host").getAsInt();
        String name = jsonObject.get("name").getAsString();
        List<Integer> activeUsers = new ArrayList<>();

        if (jsonObject.get("activeUsers") != null) {
            // parse the string back to a JsonArray
            JsonArray userList = JsonParser.parseString(jsonObject.get("activeUsers").getAsString()).getAsJsonArray();
            for (JsonElement userID : userList) {
                activeUsers.add(userID.getAsInt());
            }
        }
        return new RoomDBModel(roomID, activeUsers, host, name);
    }

    @Override
    protected JsonObject objectToJson(RoomDBModel model) {
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
