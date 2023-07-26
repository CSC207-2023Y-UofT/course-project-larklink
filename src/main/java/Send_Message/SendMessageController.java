package send_message;

import java.time.LocalDateTime;

public class SendMessageController {
    private final MessageInputBoundary inputBoundary;


    public SendMessageController(MessageInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }
    /**

     */
    public void handleSendMessage( String sender, String content, Boolean isLark){
        MessageModel request = new MessageModel(content, sender, isLark);
        inputBoundary.handleSendMessage(request);
    }
}