package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is for testing Message entity.
 */
public class MessageTest {
    private Message testMessage;

    @BeforeEach
    public void setUp() {
        testMessage = new Message("Harry", "hello");
    }

    /**
     * Tests getTimestamp method.
     */
    @Test
    public void testGetTimestamp() {
        LocalDateTime expected = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        // checks that expected timestamp is equal to actual timestamp from the message object
        // did not include milliseconds for testing
        assertEquals(expected, testMessage.getTimestamp().truncatedTo(ChronoUnit.SECONDS));
    }

    /**
     * Tests getContent method.
     */
    @Test
    public void testGetContent() {
        String timeStamp = testMessage.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String expected = "[" + timeStamp + "] Harry: hello\n";

        // checks that expected content is equal to actual content
        assertEquals(expected, testMessage.getContent());
    }

    // omitted testing formatMessage method because it is a private method

}
