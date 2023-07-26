package util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.RoomDBModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RemoteUtilities {
    /**
     Performs an HTTP request to the specified URL with the given method and JSON input.
     This method is used internally by the UserDBAccess and the RoomDBAccess class.
     @param method the HTTP method (e.g., GET, POST)
     @param jsonInputString the JSON input string for POST requests, null for GET requests
     @param id optional id for queries
     @param route api route to request from
     @return the response from the HTTP request
     @throws Exception if an error occurs during the HTTP request
     **/
    private final String url_base;

    public RemoteUtilities(String url_base) {
        this.url_base = url_base;
    }



    public RoomDBModel ConvertToRoomDB(JsonObject roomObject, JsonObject jsonObject,
                                       Integer roomID) {

        Integer host = roomObject.get("host").getAsInt();
        String name = roomObject.get("name").getAsString();

        JsonArray userList;
        if (jsonObject.get("activeUsers") == null) {
            return new RoomDBModel(roomID, new ArrayList<>(), host, name);
        }
        userList = jsonObject.get("activeUsers").getAsJsonArray();

        List<Integer> users = new ArrayList<>();

        for (JsonElement userElement : userList) {
            JsonObject userObject = userElement.getAsJsonObject();
            int userId = userObject.get("id").getAsInt();
            users.add(userId);
        }
        return new RoomDBModel(roomID, users, host, name);
    }
}
