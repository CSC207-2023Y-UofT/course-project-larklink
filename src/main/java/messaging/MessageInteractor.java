package messaging;

import database.RoomDBGateway;
import models.MessageModel;
import models.RoomDBModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

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


        LocalDateTime timestamp = LocalDateTime.now();
        if (!request.getIsLark()) { //if it's not a lark, and it's empty then we show error message
            // pass username to View like we do UserID vs. User Singleton vs. Fetch User to fix sender
            if (request.getContent() == null || request.getContent().isEmpty()) {
                presenter.prepareMessageErrorView();
                return;
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
        else {
            playLarkSound();
            String formattedMessageforLark = "[" +
                    timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] "
                    + request.getSender() + " has sent a Lark!" + "\n";

            String updatedMessageHistory = room.getMessageHistory() + formattedMessageforLark;
            room.setMessageHistory(updatedMessageHistory);
            System.out.println(room.getMessageHistory());
            database.sendAMessage(room);
            presenter.prepareRoomView(updatedMessageHistory);
        }

    }
    /**
     * Retrieves the messages and passes to the presenter
     */
    @Override
    public void handleRetrieveMessages(MessageModel request) {
        RoomDBModel room = database.getARoom(request.getRoomID());
        String messageHistory = room.getMessageHistory();
        if (messageHistory.contains("Lark")) {
            playLarkSound();
        }
        presenter.prepareRoomView(messageHistory);

    }

    private void playLarkSound() {
        try {
            // Replace "lark_sound.wav" with the actual filename of your lark sound file
            File soundFile = new File("/Users/weihanluo/Desktop/course-project-larklink/src/main/java/messaging/lark_sound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }}