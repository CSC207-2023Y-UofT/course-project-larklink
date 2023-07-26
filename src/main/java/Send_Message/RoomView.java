package send_message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class RoomView {
    private String roomId;
    private String currUserId;
    private SendMessageController sendMessageController; // Add the SendMessageController

    private String messageField;
    public RoomView( String roomId, String currUserId, SendMessageController sendMessageController, String messageField) {
        this.roomId = roomId;
        this.currUserId = currUserId;
        this.sendMessageController = sendMessageController;
        this.messageField = messageField;
    }

    public void prepareGUI() {
        JFrame frame = new JFrame();

        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessageController.handleSendMessage(currUserId, messageField, Boolean.FALSE);
            }
        });

        JButton sendLarkButton = new JButton("Send Lark");
        sendLarkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessageController.handleSendMessage(currUserId, messageField, Boolean.TRUE);
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(sendMessageButton);
        frame.add(sendLarkButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}

//Send Message - > controller -> Create messagemodel -> Interactor does things to database -> calls presenter if message else lark ->Updating Roomview message List
//After the user presses send message, the message connects with controller, which creates a new MessageModel to be processed further with the InputBoundary