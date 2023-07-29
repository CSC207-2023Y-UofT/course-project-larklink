package messaging;

import models.MessageModel;

public class MessageController {
    private final MessageInputBoundary inputBoundary;


    public MessageController(MessageInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }
    /**

     */
    public void handleSendMessage(Integer roomID, Integer sender, String content, Boolean isLark){
        MessageModel request = new MessageModel(roomID, content, sender, isLark);
        inputBoundary.handleSendMessage(request);
    }

    public void handleRetrieveMessages(Integer roomID, Integer sender, String content){
        MessageModel request = new MessageModel(roomID, content, sender, false);
        inputBoundary.handleRetrieveMessages(request);
    }
}