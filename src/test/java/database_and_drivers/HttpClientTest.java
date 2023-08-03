package database_and_drivers;

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

    private MockWebServer mockWebServer; // mock server for testing HTTP requests
    private HttpClient httpClient;      // client used to perform HTTP requests

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer(); // create mock web server
        mockWebServer.start();               // start mock web server
        String baseUrl = mockWebServer.url("/").toString(); // base URL for mock server
        httpClient = new HttpClient(baseUrl); // initialize HTTP client with mock server URL
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown(); // shutdown mock web server after test
    }

    @Test
    public void testPerformGETRequest() throws IOException {
        String expectedResponse = "Test Response"; // expected response body
        // a mock response with OK status code and expected body
        mockWebServer.enqueue(new MockResponse().setBody(expectedResponse).setResponseCode(HttpURLConnection.HTTP_OK));
        // perform GET request and assert the response equals the expected response
        String actualResponse = httpClient.performGETRequest("testRoute", 1);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testPerformPUTRequest() throws IOException {
        // a mock response with OK status code
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK));
        // perform PUT request (no return value to assert)
        httpClient.performPUTRequest("testRoute", 1, "{\"key\":\"value\"}");
    }

    @Test
    public void testPerformGETRequestError() {
        int expectedErrorCode = HttpURLConnection.HTTP_NOT_FOUND; // Expected error code
        // a mock response with NOT FOUND status code
        mockWebServer.enqueue(new MockResponse().setResponseCode(expectedErrorCode));

        // assert that an exception is thrown with the expected error code
        Exception exception = Assertions.assertThrows(IOException.class, () ->
                httpClient.performGETRequest("nonExistentRoute", 1));

        String expectedMessage = "GET request failed with response code: " + expectedErrorCode;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testPerformPUTRequestError()  {
        int expectedErrorCode = HttpURLConnection.HTTP_BAD_REQUEST; // Expected error code
        // a mock response with BAD REQUEST status code
        mockWebServer.enqueue(new MockResponse().setResponseCode(expectedErrorCode));

        // assert that an exception is thrown with the expected error code
        Exception exception = Assertions.assertThrows(IOException.class, () ->
                httpClient.performPUTRequest("testRoute", 1, "{\"key\":\"value\"}"));

        String expectedMessage = "PUT request failed with response code: " + expectedErrorCode;
        assertEquals(expectedMessage, exception.getMessage());
    }
}
