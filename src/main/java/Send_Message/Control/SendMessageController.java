package Send_Message.Control;
import Send_Message.Use_cases.MessageInputBoundary;

public class SendMessageController {
    private final MessageInputBoundary msgBoundary;


    public SendMessageController(MessageInputBoundary msgInteractor){
        this.msgBoundary = msgInteractor;
    }

    public void sendMessage(MessageInputModel msgModel){
        msgBoundary.sendMessage(msgModel);
    }
}