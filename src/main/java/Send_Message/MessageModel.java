package send_message;

import java.time.LocalDateTime;

public class MessageModel {
    private final String content;
    private final String sender;
    private final LocalDateTime timestamp;

    /**
     * Construct a message model
     * @param s a content
     * @param sender a sender
     * @param timestamp the id of a room
     */
    public MessageModel(String content, String sender, LocalDateTime timestamp){
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    /**
     * Get the content of this message model
     * @return the content of this message
     */
    public String getContent(){
        return this.content;
    }

    /**
     * Get the sender of this message model
     * @return the sender of this message
     */
    public String getSender(){
        return this.sender;
    }

    /**
     * Get the id of the room of this message model
     *
     * @return the id of a room this message is sent through
     */
    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }
}