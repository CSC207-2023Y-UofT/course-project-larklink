package database_and_drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * This class is responsible for handling HTTP requests to a specified URL base.
 */
public class HttpClient {
    private final String urlBase;

    /**
     * Constructs a new HttpClient object with the given base url.
     *
     * @param urlBase The base URL that we append to, to make API requests.
     */
    public HttpClient(String urlBase) {
        this.urlBase = urlBase;
    }

    /**
     * Sends a GET request to a specified route and returns the response as a String.
     *
     * @param route The route to send the request to, relative to the URL base.
     * @param id    The ID to include in the request, or null to send the request without an ID.
     * @return The response to the GET request, as a String.
     * @throws IOException If an I/O error occurs while sending the request or reading the response.
     */
    public String performGETRequest(String route, Integer id) throws IOException {
        URL url = new URL(urlBase + "/" + route + (id == null ? "" : "/" + id));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("GET request failed with response code: " + responseCode);
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

    /**
     * Sends a PUT request to a specified route with the given JSON input string.
     *
     * @param route            The route to send the request to, relative to the URL base.
     * @param id               The ID to include in the request.
     * @param jsonInputString  The JSON string to send in the body of the request.
     * @throws IOException     If an I/O error occurs while sending the request.
     */
    public void performPUTRequest(String route, Integer id, String jsonInputString) throws IOException {
        URL url = new URL(urlBase + "/" + route + "/" + id);
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
            throw new IOException("PUT request failed with response code: " + responseCode);
        }
    }
}
