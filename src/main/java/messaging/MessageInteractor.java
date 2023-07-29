package messaging;

import entities.Message;
import entities.Room;
import entities.User;
import database.RoomDBModel;

public class MessageInteractor implements MessageInputBoundary {
    private final MessageOutputBoundary presenter;
    private final MessageDBGateway database;


    public MessageInteractor(MessageDBGateway database, MessageOutputBoundary presenter) {
        this.presenter = presenter;
        this.database = database;
    }

    /**
     * Handles the user request for sending a message.
     */
    @Override
    public void handleSendMessage(MessageModel request) {
        // if message is empty
        if (request.getContent() == null || request.getContent().isEmpty()) {
            presenter.prepareMessageErrorView();
            return;
        }

        // create message entity
        Message msg = new Message(User.getUsername(), request.getContent());

        // create roomDBModel to update the DB w/ new message
        RoomDBModel room = database.getARoom(Room.getRoomID());
        String updatedMessageHistory = room.getMessageHistory() + msg.getContent();
        room.setMessageHistory(updatedMessageHistory);

        database.sendAMessage(room);
        presenter.prepareRoomView(updatedMessageHistory);
    }

    /**
     * Retrieves the messages and passes to the presenter
     */
    @Override
    public void handleRetrieveMessages(MessageModel request) {
        RoomDBModel room = database.getARoom(Room.getRoomID());
        presenter.prepareRoomView(room.getMessageHistory());
    }
}