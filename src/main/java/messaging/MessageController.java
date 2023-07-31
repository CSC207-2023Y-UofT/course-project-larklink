package messaging;

public class MessageController {
    private final MessageInputBoundary inputBoundary;


    public MessageController(MessageInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }
    /**

     */
    public void handleSendMessage(String content){
        MessageModel request = new MessageModel(content, false);
        inputBoundary.handleSendMessage(request);
    }

    public void handleRetrieveMessages(String content){
        MessageModel request = new MessageModel(content, false);
        inputBoundary.handleRetrieveMessages(request);
    }
}