package messaging;

import database.RoomDBGateway;
import models.MessageModel;
import models.RoomDBModel;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MessageInteractor implements MessageInputBoundary {
    private final MessageOutputBoundary presenter;
    private final RoomDBGateway database;
    private final String larkSoundFilePath;

    /**
     * Constructs a MessageInteractor object with the given dependencies.
     *
     * @param database           The RoomDBGateway to access room-related data.
     * @param presenter          The MessageOutputBoundary to present messages to the user.
     * @param larkSoundFilePath  The file path to the lark sound file.
     */
    public MessageInteractor(RoomDBGateway database, MessageOutputBoundary presenter, String larkSoundFilePath) {
        this.presenter = presenter;
        this.database = database;
        this.larkSoundFilePath = larkSoundFilePath;
    }

    /**
     * Handles the user request for sending a message.
     *
     * @param request The MessageModel representing the user's message.
     */
    @Override
    public void handleSendMessage(MessageModel request) {
        RoomDBModel room = database.getARoom(request.getRoomID());

        LocalDateTime timestamp = LocalDateTime.now();

        if (request.getContent() == null || request.getContent().isEmpty()) {
            presenter.prepareMessageErrorView();
            return;
        }

        if (request.getContent().contains("/lark")) {
            playLarkSound();
        }

        String formattedMessage = "[" +
                timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] "
                + request.getSender() + ": " + request.getContent() + "\n";

        String updatedMessageHistory = room.getMessageHistory() + formattedMessage;
        room.setMessageHistory(updatedMessageHistory);
        System.out.println(room.getMessageHistory());
        database.sendAMessage(room);
        presenter.prepareRoomView(updatedMessageHistory);
    }

    /**
     * Retrieves the messages and passes them to the presenter.
     *
     * @param request The MessageModel representing the user's request for message retrieval.
     */
    @Override
    public void handleRetrieveMessages(MessageModel request) {
        RoomDBModel room = database.getARoom(request.getRoomID());
        String messageHistory = room.getMessageHistory();

        if (messageHistory.contains("/lark")) {
            playLarkSound();
        }

        presenter.prepareRoomView(messageHistory);
    }

    /**
     * Plays the Lark Sound.
     *
     */
    private void playLarkSound() {
        try {
            String userDir = System.getProperty("user.dir");
            File soundFile = new File(userDir + larkSoundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
