package send_message;

import java.time.LocalDateTime;

public class SendMessageInteractor implements MessageInputBoundary {
    private final SendMessageOutputBoundary presenter;
    private final RoomMessageHistoryDBGateway messageDatabase;


    public SendMessageInteractor(SendMessageOutputBoundary presenter, RoomMessageHistoryDBGateway messageDatabase) {
        this.presenter = presenter;
        this.messageDatabase = messageDatabase;

    }

    /**
     * Handles the user request for sending a message.
     */
//     if (username.isEmpty()) {
//        JOptionPane.showMessageDialog(null, "Username field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
    @Override
    public void handleSendMessage(MessageModel request) {

        String sender = request.getSender();
        String content = request.getContent();
        if (content == null || content.isEmpty()) {
            return; // Return early if the content is null or empty
        }
        LocalDateTime timestamp = LocalDateTime.now();
        //Create a MessageDBModel from a MessageModel by using only the sender and the content and adding timestamp
        MessageDBModel messageDBModel = new MessageDBModel(sender, content, timestamp);
        messageDatabase.addMessage(messageDBModel);
        presenter.prepareRoomView(1, 1, content);

    }


}