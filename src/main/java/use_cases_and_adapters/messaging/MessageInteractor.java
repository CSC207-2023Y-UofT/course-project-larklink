package use_cases_and_adapters.messaging;

import entities.Message;
import entities.Room;
import entities.User;
import use_cases_and_adapters.RoomDBModel;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MessageInteractor implements MessageInputBoundary {
    private final MessageOutputBoundary presenter;
    private final MessageDBGateway database;
    private final String larkSoundFilePath;

    /**
     * Constructs a MessageInteractor object with the given dependencies.
     *
     * @param database           The RoomDBGateway to access room-related data.
     * @param presenter          The MessageOutputBoundary to present messages to the user.
     * @param larkSoundFilePath  The file path to the lark sound file.
     */
    public MessageInteractor(MessageDBGateway database, MessageOutputBoundary presenter, String larkSoundFilePath) {
        this.presenter = presenter;
        this.database = database;
        this.larkSoundFilePath = larkSoundFilePath;
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
            playLarkSound();
        }

        // create message entity
        Message msg = new Message(User.getUsername(), content);

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
    public void handleRetrieveMessages() {
        RoomDBModel room = database.getARoom(Room.getRoomID());
        String messageHistory = room.getMessageHistory();

        if (messageHistory.contains("/lark")) {
            playLarkSound();
        }

        presenter.prepareRoomView(messageHistory);
    }

    /**
     * Plays the Lark Sound.
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