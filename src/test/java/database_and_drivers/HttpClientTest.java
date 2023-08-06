package database_and_drivers;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.HttpURLConnection;

public class HttpClientTest {

    private MockWebServer mockWebServer;
    private HttpClient httpClient;

    @BeforeEach
    public void setUp() throws IOException {
        // create mock web server
        mockWebServer = new MockWebServer();
        // start mock web server
        mockWebServer.start();
        // base URL for mock server
        String baseUrl = mockWebServer.url("/").toString();
        // initialize HTTP client with mock server URL
        httpClient = new HttpClient(baseUrl);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // shutdown mock web server after test
        mockWebServer.shutdown();
    }

    @Test
    public void testPerformGETRequest() throws IOException {
        String expectedResponse = "Test Response";
        // a mock response with OK status code and expected body
        mockWebServer.enqueue(new MockResponse().setBody(expectedResponse).setResponseCode(HttpURLConnection.HTTP_OK));
        // perform GET request and assert the response equals the expected response
        String actualResponse = httpClient.performGETRequest("testRoute", 1);
        assert actualResponse.equals(expectedResponse);
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
        assert exception.getMessage().equals(expectedMessage);
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
        assert exception.getMessage().equals(expectedMessage);
    }
}
