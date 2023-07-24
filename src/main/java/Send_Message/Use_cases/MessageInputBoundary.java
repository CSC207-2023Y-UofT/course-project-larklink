package Send_Message.Use_cases;
import Send_Message.Control.MessageInputModel;
import Send_Message.Presenter.MessageOutputModel;
public interface MessageInputBoundary {
    MessageOutputModel sendMessage(MessageInputModel msgModel);
}
