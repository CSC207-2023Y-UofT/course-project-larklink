package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This Message class represents a message where each message has a timestamp, a sender, and content.
 */
public class Message {
    private final LocalDateTime timestamp;
    private final String sender;
    private final String content;

    /**
     * Constructs a new Message instance with a current timestamp, a sender and a content.
     * The message content is formatted to include the timestamp, the sender, and the content.
     * @param sender The sender of the message.
     * @param content The content of the message.
     */
    public Message(String sender, String content) {
        this.timestamp = LocalDateTime.now();
        this.sender = sender;
        this.content = formatMessage(content);
    }

    /**
     * A getter for content of the message.
     *
     * @return The content of the message.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * A getter for timestamp of the message.
     *
     * @return The timestamp of the message.
     */
    public LocalDateTime getTimestamp() { return this.timestamp; }

    /**
     * Formats the message content to include the timestamp, the sender, and the content.
     * @param content The content of the message.
     * @return The formatted message.
     */
    private String formatMessage(String content) {
        return "[" + timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] "
                + sender + ": " + content + "\n";
    }
}
