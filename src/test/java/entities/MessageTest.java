package entities;

import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.*;


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
    public void testFormatMessage() {
        String formattedMessage = "[" + testMessage.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] "
                + testSender + ": " + testContent + "\n";
        assert testMessage.getContent().equals(formattedMessage);

    }
    // omitted testing formatMessage method because it is a private method
}
