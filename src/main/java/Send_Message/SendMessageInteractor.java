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
     * @param msgModel the message model containing sender, recipient, and message content
     */
//     if (username.isEmpty()) {
//        JOptionPane.showMessageDialog(null, "Username field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
    @Override
    public void handleSendMessage(MessageModel msgModel) {
        if  (msgModel.getContent() == null) {
            // Invalid input, notify the presenter with an error message
            presenter.prepareMessageErrorView();
            return;
        }
        String sender = msgModel.getSender();
        String content = msgModel.getContent();
        LocalDateTime time = msgModel.getTimestamp();

        MessageDBModel new_message = new MessageDBModel(sender, content, time);
        messageDatabase.addMessage(new_message);
        presenter.prepareRoomView(1, 1, content);

    }


}