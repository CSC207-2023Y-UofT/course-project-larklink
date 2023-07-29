package messaging;

import database.RoomDBGateway;
import entities.User;
import models.MessageModel;
import models.RoomDBModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageInteractor implements MessageInputBoundary {
    private final MessageOutputBoundary presenter;
    private final RoomDBGateway database;


    public MessageInteractor(RoomDBGateway database, MessageOutputBoundary presenter) {
        this.presenter = presenter;
        this.database = database;
    }

    /**
     * Handles the user request for sending a message.
     */
    @Override
    public void handleSendMessage(MessageModel request) {
        RoomDBModel room = database.getARoom(request.getRoomID());

        // If message is empty
        if (request.getContent() == null || request.getContent().isEmpty()) {
            presenter.prepareMessageErrorView();
        }

        LocalDateTime timestamp = LocalDateTime.now();
        String formattedMessage = "[" +
                timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] "
                + User.getUsername() + ": " + request.getContent() + "\n";

        String updatedMessageHistory = room.getMessageHistory() + formattedMessage;
        room.setMessageHistory(updatedMessageHistory);
        System.out.println(room.getMessageHistory());
        database.sendAMessage(room);

        presenter.prepareRoomView(updatedMessageHistory);
    }

    /**
     * Retrieves the messages and passes to the presenter
     */
    @Override
    public void handleRetrieveMessages(MessageModel request) {
        RoomDBModel room = database.getARoom(request.getRoomID());
        presenter.prepareRoomView(room.getMessageHistory());
    }
}