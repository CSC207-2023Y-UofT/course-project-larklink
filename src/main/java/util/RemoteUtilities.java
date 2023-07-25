package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RemoteUtilities {
    /**
     Performs an HTTP request to the specified URL with the given method and JSON input.
     This method is used internally by the UserDBAccess class.
     @param method the HTTP method (e.g., GET, POST)
     @param jsonInputString the JSON input string for POST requests, null for GET requests
     @return the response from the HTTP request
     @throws Exception if an error occurs during the HTTP request
     **/
    private final String url_base;

    public RemoteUtilities(String url_base) {
        this.url_base = url_base;
    }

    public String performHttpRequest(String method, String jsonInputString, Integer id,
                                      String route) throws Exception {
        URL url;
        if (id != null) {
            url = new URL(url_base + "/" + route + "/" + id);
        } else {
            url = new URL(url_base + "/" + route);
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
