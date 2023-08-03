package use_cases_and_adapters.messaging;

import entities.Message;
import entities.Room;
import entities.User;
import use_cases_and_adapters.RoomDBModel;

/**
 * The MessageInteractor class implements the MessageInputBoundary interface and is responsible for handling
 * message-related requests. It interacts with the database and presenter to manage messages and their presentation.
 */
public class MessageInteractor implements MessageInputBoundary {
    private final MessageDBGateway database;
    private final MessageOutputBoundary presenter;
    private final LarkSoundPlayerGateway larkSoundPlayer;

    /**
     * Constructs a MessageInteractor object with the given dependencies.
     *
     * @param database           The RoomDBGateway to access room-related data.
     * @param presenter          The MessageOutputBoundary to present messages to the user.
     * @param larkSoundPlayer    The LarkSoundPlayerGateway to allow this interactor to play the lark sound when required.
     */
    public MessageInteractor(MessageDBGateway database, MessageOutputBoundary presenter, LarkSoundPlayerGateway larkSoundPlayer) {
        this.database = database;
        this.presenter = presenter;
        this.larkSoundPlayer = larkSoundPlayer;
    }

    /**
     * Handles the user request for sending a message.
     */
    @Override
    public void handleSendMessage(String content) {
        // if message is empty
        if (content == null || content.isEmpty()) {
            presenter.prepareMessageErrorView();
            return;
        }

        if (content.contains("/lark")) {
            larkSoundPlayer.playLarkSound();
        }

        // create message entity
        Message msg = new Message(User.getUsername(), content);

        // create roomDBModel to update the DB w/ new message
        RoomDBModel room = database.getARoom(Room.getRoomID());
        String updatedMessageHistory = room.getMessageHistory() + msg.getContent();
        room.setMessageHistory(updatedMessageHistory);
        database.updateARoom(room);
        presenter.prepareRoomView(updatedMessageHistory);
    }

    /**
     * Retrieves the messages and passes to the presenter.
     */
    @Override
    public void handleRetrieveMessages() {
        RoomDBModel room = database.getARoom(Room.getRoomID());
        String messageHistory = room.getMessageHistory();
        String[] messages = messageHistory.split("\\n");
        String mostRecentMessage = messages[messages.length - 1];
        if (mostRecentMessage.contains("/lark")) { //checks whether the most recent message contains /lark
            larkSoundPlayer.playLarkSound();
        }

        presenter.prepareRoomView(messageHistory);
    }
}
