package messaging;

import entities.Message;
import entities.Room;
import entities.User;
import database.RoomDBModel;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MessageInteractor implements MessageInputBoundary {
    private final MessageDBGateway database;
    private final MessageOutputBoundary presenter;
    private final String larkSoundFilePath;

    /**
     * Constructs a MessageInteractor object with the given dependencies.
     *
     * @param database           The RoomDBGateway to access room-related data.
     * @param presenter          The MessageOutputBoundary to present messages to the user.
     * @param larkSoundFilePath  The file path to the lark sound file.
     */
    public MessageInteractor(MessageDBGateway database, MessageOutputBoundary presenter, String larkSoundFilePath) {
        this.database = database;
        this.presenter = presenter;
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
        String[] messages = messageHistory.split("\\n");
        String mostRecentMessage = messages[messages.length - 1];
        if (mostRecentMessage.contains("/lark")) { //checks whether the most recent message contains /lark
            playLarkSound();
        }

        presenter.prepareRoomView(messageHistory);
    }

    /**
     * Plays the Lark Sound.
     */
    void playLarkSound() {
        AudioInputStream audioInputStream = null;
        try {
            String userDir = System.getProperty("user.dir");
            File soundFile = new File(userDir + larkSoundFilePath);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } finally {
            if (audioInputStream != null) {
                try {
                    audioInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }}
