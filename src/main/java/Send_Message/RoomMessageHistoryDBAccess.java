package send_message;


import java.util.List;
import java.util.ArrayList;
public class RoomMessageHistoryDBAccess implements RoomMessageHistoryDBGateway{
    private List<MessageDBModel> messageList;
    public RoomMessageHistoryDBAccess() {
        // Initialize the messageList in the constructor
        messageList = new ArrayList<>();
    }
    @Override
    public List<MessageDBModel> loadMessages() {
        return messageList;
    }

    @Override
    public void addMessage(MessageDBModel message) {
        messageList.add(message);
    }
}
