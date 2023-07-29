package messaging;

public class MessageModel {
    private final String content;
    private final Boolean isLark;

    /**
     * Construct a message model
     *
     * @param content the content
     * @param isLark  whether the message was a Lark
     */
    public MessageModel(String content, Boolean isLark){
        this.content = content;
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

     * @return whether the message is sent as a Lark
     */
    public Boolean getIsLark(){
        return this.isLark;
    }
}