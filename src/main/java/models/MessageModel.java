package models;

public class MessageModel {
    private final Integer roomID;
    private final String content;
    private final Integer sender;

    /**
     * Construct a message model
     *
     * @param content the content
     * @param sender a sender
     */
    public MessageModel(Integer roomID, String content, Integer sender){
        this.roomID = roomID;
        this.content = content;
        this.sender = sender;
    }

    public Integer getRoomID(){
        return this.roomID;
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
    public Integer getSender(){
        return this.sender;
    }
}