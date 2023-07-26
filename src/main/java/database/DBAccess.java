package database;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import util.RemoteUtilities;

import java.util.ArrayList;
import java.util.List;

public abstract class DBAccess<T> {
    protected final String urlBase;
    protected final RemoteUtilities remoteUtilities;
    protected final Gson gson;

    public DBAccess(String urlBase) {
        this.urlBase = urlBase;
        this.remoteUtilities = new RemoteUtilities(urlBase);
        this.gson = new Gson();
    }

    public T fetch(Integer id) {
        try {
            String response = performHttpRequest("GET", null, id);
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
            System.out.println(jsonObject);
            return parseJsonToObject(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> loadAll() {
        try {
            String response = performHttpRequest("GET", null, null);
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
            JsonArray jsonArray = jsonObject.get(getRoute()).getAsJsonArray();
            List<T> result = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                result.add(parseJsonToObject(element.getAsJsonObject()));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void add(T model) {
        try {
            String jsonInputString = createJsonObjectFromModel(model).toString();
            System.out.println(jsonInputString);
            performHttpRequest("POST", jsonInputString, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(Integer id, T model) {
        try {
            String jsonInputString = createJsonObjectFromModel(model).toString();
            performHttpRequest("PUT", jsonInputString, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract T parseJsonToObject(JsonObject jsonObject);

    protected abstract JsonObject createJsonObjectFromModel(T model);

    private String performHttpRequest(String method, String jsonInputString, Integer id) throws Exception {
        return remoteUtilities.performHttpRequest(method, jsonInputString, id, getRoute());
    }

    protected abstract String getRoute();
}
