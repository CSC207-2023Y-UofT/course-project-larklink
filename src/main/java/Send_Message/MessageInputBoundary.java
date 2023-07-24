package send_message;
import send_message.MessageInputModel;
import send_message.MessageOutputModel;
public interface MessageInputBoundary {
    void handleSendMessage(MessageInputModel msgModel);
}
