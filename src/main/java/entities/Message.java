package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final LocalDateTime timestamp;
    private final String sender;
    private final String content;

    public Message(String sender, String content) {
        this.timestamp = LocalDateTime.now();
        this.sender = sender;
        this.content = formatMessage(content);
    }

    public String getContent() {
        return this.content;
    }

    private String formatMessage(String content) {
        return "[" + timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] "
                + sender + ": " + content + "\n";
    }
}
