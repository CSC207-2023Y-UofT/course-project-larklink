package database;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class DBAccess<T> {
    private final JsonConverter<T> converter;
    private final HttpClient httpClient;
    private final Gson gson;

    public DBAccess(HttpClient httpClient, JsonConverter<T> converter) {
        this.httpClient = httpClient;
        this.converter = converter;
        this.gson = new Gson();
    }

    protected List<T> getRows() throws IOException {
        String response = httpClient.performGETRequest(getRoute(), null);
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = jsonObject.get(getRoute()).getAsJsonArray();
        List<T> result = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            result.add(converter.toObject(element.getAsJsonObject()));
        }
        return result;
    }

    protected T getARow(Integer rowID) throws IOException {
        String response = httpClient.performGETRequest(getRoute(), rowID);
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return converter.toObject(jsonObject);
    }

    protected void updateARow(Integer rowID, T model) throws IOException {
        String jsonInputString = converter.toJson(model).toString();
        httpClient.performPUTRequest(getRoute(), rowID, jsonInputString);
    }

    protected abstract String getRoute();
}
