package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
    private String testSender;
    private String testContent;
    private Message testMessage;

    @BeforeEach
    public void setUp() {
        testSender = "Harry";
        testContent = "hello";
        testMessage = new Message(testSender, testContent);
    }

    @Test
    public void testFormatMessage() {
        String formattedMessage = "[" + testMessage.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] "
                + testSender + ": " + testContent + "\n";
        assertEquals(formattedMessage, testMessage.getContent());
    }

}
