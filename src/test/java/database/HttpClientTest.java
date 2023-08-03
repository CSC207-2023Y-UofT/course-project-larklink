package database;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpClientTest {

    private MockWebServer mockWebServer;
    private HttpClient httpClient;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String baseUrl = mockWebServer.url("/").toString();
        httpClient = new HttpClient(baseUrl);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testPerformGETRequest() throws IOException {
        String expectedResponse = "Test Response";
        mockWebServer.enqueue(new MockResponse().setBody(expectedResponse).setResponseCode(HttpURLConnection.HTTP_OK));
        String actualResponse = httpClient.performGETRequest("testRoute", 1);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testPerformPUTRequest() throws IOException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK));
        httpClient.performPUTRequest("testRoute", 1, "{\"key\":\"value\"}");
        // no assertions since this method has no return value
    }

    @Test
    public void testPerformGETRequestError() {
        int expectedErrorCode = HttpURLConnection.HTTP_NOT_FOUND;
        mockWebServer.enqueue(new MockResponse().setResponseCode(expectedErrorCode));

        Exception exception = Assertions.assertThrows(IOException.class, () -> {
            httpClient.performGETRequest("nonExistentRoute", 1);
        });

        String expectedMessage = "GET request failed with response code: " + expectedErrorCode;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testPerformPUTRequestError()  {
        int expectedErrorCode = HttpURLConnection.HTTP_BAD_REQUEST;
        mockWebServer.enqueue(new MockResponse().setResponseCode(expectedErrorCode));
        Exception exception = Assertions.assertThrows(IOException.class, () -> {
            httpClient.performPUTRequest("testRoute", 1, "{\"key\":\"value\"}");
        });

        String expectedMessage = "PUT request failed with response code: " + expectedErrorCode;
        assertEquals(expectedMessage, exception.getMessage());
    }
}