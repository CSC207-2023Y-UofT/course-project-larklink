package Send_Message.Control;
//The MessageModel is similar to the MessageEnt except that it handles incoming messages
public class MessageInputModel {
    private final String content;
    private final String sender;
    private final String roomId;

    /**
     * Construct a message model
     * @param s a content
     * @param sender a sender
     * @param roomId the id of a room
     */
    public MessageInputModel(String s, String sender, String roomId){
        this.content = s;
        this.sender = sender;
        this.roomId = roomId;
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
     * @return the id of a room this message is sent through
     */
    public String getRoomId() {
        return this.roomId;
    }
}