package messaging;

import models.MessageModel;

public class MessageController {
    private final MessageInputBoundary inputBoundary;


    public MessageController(MessageInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }
    /**

     */
    public void handleSendMessage(Integer roomID, String content){
        MessageModel request = new MessageModel(roomID, content, false);
        inputBoundary.handleSendMessage(request);
    }

    public void handleRetrieveMessages(Integer roomID, String content){
        MessageModel request = new MessageModel(roomID, content, false);
        inputBoundary.handleRetrieveMessages(request);
    }
}