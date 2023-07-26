package send_message;

import java.time.LocalDateTime;

public class SendMessageController {
    private final MessageInputBoundary inputBoundary;


    public SendMessageController(MessageInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }
    /**

     */
    public void handleSendMessage(String content, String sender, LocalDateTime timestamp){
        MessageModel request = new MessageModel(content, sender, timestamp);
        inputBoundary.handleSendMessage(request);
    }
}