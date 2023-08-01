package database.converters;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import use_cases.RoomDBModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for converting RoomDBModel instances to and from JSON.
 */
public class RoomConverter implements JsonConverter<RoomDBModel> {

    /**
     * Converts a JsonObject into a RoomDBModel instance.
     *
     * @param jsonObject The JsonObject to convert. This should include properties for "id", "host", "name",
     * "activeUsers", and "messageHistory". If the JsonObject has a "room" property, the JsonObject for the room will be extracted.
     * @return A RoomDBModel instance with properties set according to the given JsonObject.
     */
    @Override
    public RoomDBModel toObject(JsonObject jsonObject) {
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

        String messageHistory = jsonObject.get("messageHistory") != null ? jsonObject.get("messageHistory").getAsString() : "";

        return new RoomDBModel(roomID, name, host, activeUsers, messageHistory);
    }

    /**
     * Converts a RoomDBModel instance into a JsonObject.
     *
     * @param model The RoomDBModel instance to convert. This should include properties for "host", "name",
     * "activeUsers", and "messageHistory".
     * @return A JsonObject representing the given RoomDBModel instance.
     */
    @Override
    public JsonObject toJson(RoomDBModel model) {
        JsonObject roomObject = new JsonObject();
        roomObject.addProperty("host", model.getHostID());
        roomObject.addProperty("name", model.getRoomName());

        JsonArray activeUsers = new JsonArray();
        for (Integer userId : model.getActiveUserIDs()) {
            activeUsers.add(userId);
        }
        roomObject.addProperty("activeUsers", activeUsers.toString());

        roomObject.addProperty("messageHistory", model.getMessageHistory());

        JsonObject mainObject = new JsonObject();
        mainObject.add("room", roomObject);

        return mainObject;
    }
}
