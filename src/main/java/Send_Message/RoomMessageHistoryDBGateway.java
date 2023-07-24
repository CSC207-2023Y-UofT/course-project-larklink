package send_message;

import java.util.List;
public interface RoomMessageHistoryDBGateway {
    List<MessageDBModel> loadMessages();

    void addMessage(MessageDBModel message);
}
