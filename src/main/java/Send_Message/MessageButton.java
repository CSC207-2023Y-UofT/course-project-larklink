package Send_Message;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//The following code initializes a button called SendMessage and after pressing it prints "Message Sent"
public class MessageButton extends JFrame{
    public MessageButton() {
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Send Message Button Example");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Call a method to create and add components to the frame
        createAndAddComponents();
    }

    private void createAndAddComponents() {
        // Create a button
        JButton sendMessageButton = new JButton("Send Message");

        // Add an ActionListener to the button
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle the button click event
                // Add the logic to send the message here
                System.out.println("Message Sent!");
            }
        });

        // Add the button to the frame
        add(sendMessageButton);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MessageButton().setVisible(true);
            }
        });
    }
}
