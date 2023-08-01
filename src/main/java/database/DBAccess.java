package database;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class DBAccess<T> {
    protected final String urlBase;
    protected final Gson gson;

    public DBAccess(String urlBase) {
        this.urlBase = urlBase;
        this.gson = new Gson();
    }

    protected List<T> getRows() {
        try {
            String response = performGETRequest(null);
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
            JsonArray jsonArray = jsonObject.get(getRoute()).getAsJsonArray();
            List<T> result = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                result.add(jsonToObject(element.getAsJsonObject()));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    protected T getARow(Integer rowID) {
        try {
            String response = performGETRequest(rowID);
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
            return jsonToObject(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void updateARow(Integer rowID, T model) {
        try {
            String jsonInputString = objectToJson(model).toString();
            performPUTRequest(rowID, jsonInputString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract T jsonToObject(JsonObject jsonObject);

    protected abstract JsonObject objectToJson(T model);

    protected abstract String getRoute();

    private void performPUTRequest(Integer id, String jsonInputString) throws IOException {
        URL url = new URL(urlBase + "/" + getRoute() + "/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush();
        }
        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.err.println("HTTP request failed with response code: " + responseCode);
        }
    }

    private String performGETRequest(Integer id) throws IOException {
        URL url = new URL(urlBase + "/" + getRoute() + (id == null ? "" : "/" + id));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.err.println("HTTP request failed with response code: " + responseCode);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            conn.disconnect();
            return response.toString();
        }
    }
}
