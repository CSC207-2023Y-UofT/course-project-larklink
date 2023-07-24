package signup_and_login;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserDBAccess implements UserDBGateway {
    private final String urlBase;

    public UserDBAccess(String urlBase) {
        this.urlBase = urlBase;
    }

    /**
     * Loads the users from the database.
     * @return a list of UserModel objects representing the existing users
     */
    @Override
    public List<UserDBModel> loadUsers() {
        try {
            String result = performHttpRequest("GET", null, null);

            // Parse JSON data
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            JsonArray usersArray = jsonObject.get("users").getAsJsonArray();
            List<UserDBModel> users = new ArrayList<>();
            for (JsonElement userElement : usersArray) {
                JsonObject userObject = userElement.getAsJsonObject();
                int userId = userObject.get("id").getAsInt();
                String username = userObject.get("username").getAsString();
                String password = userObject.get("password").getAsString();
                users.add(new UserDBModel(userId, username, password));
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Fetches a specific user from the database by their userID
     * @return a UserModel object representing the user
     */
    @Override
    public UserDBModel fetchUser(int userID) {
        try {
            String result = performHttpRequest("GET", null, userID);

            // Parse JSON data
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            JsonObject userObject = jsonObject.get("user").getAsJsonObject();

            String username = userObject.get("username").getAsString();
            String password = userObject.get("password").getAsString();

            return new UserDBModel(userID, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Saves a new user to the database (effectively signing them up).
     * @param request the UserModel object representing the new user
     */
    @Override
    public void saveNewUser(UserDBModel request) {
        try {
            JsonObject userObject = new JsonObject();
            userObject.addProperty("username", request.getUsername());
            userObject.addProperty("password", request.getPassword());

            JsonObject mainObject = new JsonObject();
            mainObject.add("user", userObject);

            String jsonInputString = mainObject.toString();
            performHttpRequest("POST", jsonInputString, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     Performs an HTTP request to the specified URL with the given method and JSON input.
     This method is used internally by the UserDBAccess class.
     @param method the HTTP method (e.g., GET, POST)
     @param jsonInputString the JSON input string for POST requests, null for GET requests
     @return the response from the HTTP request
     @throws Exception if an error occurs during the HTTP request
     **/
    private String performHttpRequest(String method, String jsonInputString, Integer id) throws Exception {
        URL url;
        if (id != null) {
            url = new URL(urlBase + "/users/" + id);
        } else {
            url = new URL(urlBase + "/users");
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);

        if (method.equals("POST")) {
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
            }
        }

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