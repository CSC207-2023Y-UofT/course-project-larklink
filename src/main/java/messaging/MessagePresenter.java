package messaging;
import javax.swing.*;

import ui.View;

public class MessagePresenter implements MessageOutputBoundary {
    private View view;

    @Override
    public void prepareRoomView(String messageHistory) {
        view.setMessageHistory(messageHistory);
        view.prepareGUI();
    }

    @Override
    public void prepareMessageErrorView() {
        JOptionPane.showMessageDialog(null, "Error sending message, cannot send empty message!", "Message Error", JOptionPane.ERROR_MESSAGE);
    }

//    private void playLarkSound() {
//        try {
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("lark_sound.wav").getAbsoluteFile());
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//            clip.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void setView(View view) {
        this.view = view;
    }

}


