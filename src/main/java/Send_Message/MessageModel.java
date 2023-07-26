package send_message;

import java.time.LocalDateTime;

public class MessageModel {
    private final String content;
    private final String sender;
    private final Boolean isLark;

    /**
     * Construct a message model
     * @param content the content
     * @param sender a sender
     * @param isLark whether the message was a Lark
     */
    public MessageModel(String content, String sender, Boolean isLark){
        this.content = content;
        this.sender = sender;
        this.isLark = isLark;
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

     * @return whether the message is sent as a Lark
     */
    public Boolean getTimestamp(){
        return this.isLark;
    }
}