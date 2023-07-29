package ui;

import leave_room.LeaveRoomController;
import messaging.MessageController;
import javax.swing.*;
import java.awt.*;


public class RoomView extends View {
    private final LeaveRoomController leaveRoomController;
    private final MessageController sendMessageController;
    private JTextField messageTextField;
    private JTextArea chatTextArea;

    public RoomView(LeaveRoomController leaveRoomController, MessageController sendMessageController){
        this.leaveRoomController = leaveRoomController;
        this.sendMessageController = sendMessageController;
    }

    @Override
    protected JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set up the JFrame
        chatTextArea = new JTextArea(20, 50);
        chatTextArea.setText(messageHistory);
        chatTextArea.setLineWrap(true); // Enable line wrapping
        chatTextArea.setEditable(false);

        // Scroll through texts
        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create a JTextField to enter messages
        messageTextField = new JTextField(30);



        // Create a SendMessage
        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(e -> sendMessageController.handleSendMessage(roomID, userID, messageTextField.getText()));


        // Send Lark Button
        JButton sendLarkButton = new JButton("Send Lark");
        // sendLarkButton.addActionListener(e -> sendMessageController.handleSendMessage(userID, "sent a lark!", true));


        // Leave Room
        JButton leaveButton = new JButton("Leave Room");
        leaveButton.addActionListener(e -> leaveRoomController.handleLeaveRoom(roomID, userID));

        // Refresh Button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> sendMessageController.handleRetrieveMessages(roomID, userID,messageTextField.getText()));

        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.add(messageTextField, BorderLayout.CENTER);
        inputPanel.add(sendMessageButton, BorderLayout.LINE_END);
        inputPanel.add(sendLarkButton, BorderLayout.LINE_START);
        inputPanel.add(refreshButton, BorderLayout.PAGE_END);

        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(inputPanel, BorderLayout.PAGE_END);

        panel.add(chatPanel, BorderLayout.CENTER);
        panel.add(leaveButton, BorderLayout.PAGE_END);

        return panel;
    }
}
//Tasks
//Todo: handleRetrieveMessages
//ToDO: Update Messages
//ToDo: SendLark
