package send_message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RoomView extends JFrame {
    private final JTextArea messageTextArea;
    private final JTextField inputTextField;
    private final JButton sendMessageButton;
    private final JButton sendLarkButton;
    private final List<String> messages;
    private final SendMessageController controller;
    private final int RoomID; // RoomID of the current room
    private final String currUserID;


    public RoomView(SendMessageController controller, int RoomID, String currentUserID) {
        this.controller = controller;
        this.RoomID = RoomID;
        this.currUserID = currentUserID;


        // Set up the JFrame
        setTitle("Chat Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Initialize the list of messages
        messages = new ArrayList<>();

        // Create a JTextArea to display the messages
        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);

        // Create a JScrollPane to scroll through the messages
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create a JTextField for entering messages
        inputTextField = new JTextField(30);

        // Create "Send Message" button
        sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Create "Send Lark" button
        sendLarkButton = new JButton("Send Lark");
        sendLarkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendLark();
            }
        });

        // Set up the layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JLabel roomIDLabel = new JLabel("Room ID: " + RoomID);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(inputTextField);
        bottomPanel.add(sendMessageButton);
        bottomPanel.add(sendLarkButton);

        add(bottomPanel, BorderLayout.SOUTH);
        add(roomIDLabel, BorderLayout.NORTH);
    }

    // Method to display a new message in the chat room
    public void displayMessage(String message, LocalDateTime localtime) {
        String messageWithTime = "[" + localtime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + message;
        messages.add(messageWithTime);
        StringBuilder sb = new StringBuilder();
        for (String msg : messages) {
            sb.append(msg).append("\n");
        }
        messageTextArea.setText(sb.toString());
    }

    // Method to get the message entered by the user in the input text field
    public String getInputMessage() {
        return inputTextField.getText();
    }
    public LocalDateTime getLocalTime(){
        return LocalDateTime.now();
    }
    // Method to clear the input text field after sending a message
    public void clearInput() {
        inputTextField.setText("");
    }

    // Method to handle the "Send Message" button click
    private void sendMessage() {
        LocalDateTime localtime = getLocalTime();
        String message = getInputMessage();
        // Check if the message is not empty
        if (!message.isEmpty()) {
            displayMessage(message, localtime);
//            presenter.prepareRoomView(1,1, message);
            clearInput();
        }
    }

    // Method to handle the "Send Lark" button click
    private void sendLark() {
        // Not implemented yet
        LocalDateTime localtime = getLocalTime();
        displayMessage("You sent a Lark!", localtime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RoomMessageHistoryDBGateway messageDatabase = new RoomMessageHistoryDBAccess(); // Replace this with the actual implementation
                SendMessageOutputBoundary presenter = new RoomPresenter(); // Replace this with your implementation
                SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(presenter, messageDatabase);
                SendMessageController SendMessageController = null;
                RoomView roomView = new RoomView(SendMessageController, 13, "Bob");
                roomView.setVisible(true);
            }
        });
    }
}

//Send Message - > controller -> Create messagemodel -> Interactor does things to database -> calls presenter if message else lark ->Updating Roomview message List
//After the user presses send message, the message connects with controller, which creates a new MessageModel to be processed further with the InputBoundary