package entities;

import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.*;

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
        assert testMessage.getContent().equals(formattedMessage);
    }

}
