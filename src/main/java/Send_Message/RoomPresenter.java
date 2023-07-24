package send_message;
import javax.swing.*;
import java.util.List;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class RoomPresenter implements SendMessageOutputBoundary{
    private List<String> messageList;
    private JTextArea chatTextArea;
    @Override
    public void prepareRoomView() {
        //Updating the messages being shown
        // list of message content
        for (String message : messageList) {
            chatTextArea.append(message + "\n"); // Append each message to the JTextArea
        }
    }

    @Override
    public void prepareRoomViewForLark() {
       //PLay lark sound once
    }

    @Override
    public void prepareMessageErrorView() {
        JOptionPane.showMessageDialog(null, "Error sending message!", "Message Error", JOptionPane.ERROR_MESSAGE);
    }
    private void playLarkSound() {
        try {
            // Load the sound file (replace "lark_sound.wav" with the actual path of your sound file)
            File soundFile = new File("lark_sound.wav");

            // Create an AudioInputStream to read the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            // Get the AudioFormat of the sound
            AudioFormat format = audioInputStream.getFormat();

            // Create a DataLine.Info object to check if the audio format is supported
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Check if the format is supported
            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Audio format not supported!");
                return;
            }

            // Get a Clip object to play the sound
            Clip clip = (Clip) AudioSystem.getLine(info);

            // Open the AudioInputStream
            clip.open(audioInputStream);

            // Start playing the sound
            clip.start();

            // Wait for the sound to finish playing
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}


