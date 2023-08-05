package views;

import org.junit.jupiter.api.Test;
import use_cases_and_adapters.leave_room.LeaveRoomController;
import use_cases_and_adapters.messaging.MessageController;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RoomViewTest {

    @Test
    void testCreatePanel() {
        // Create mock instances of LeaveRoomController and MessageController
        LeaveRoomController leaveRoomController = mock(LeaveRoomController.class);
        MessageController sendMessageController = mock(MessageController.class);

        // Create the RoomView
        RoomView roomView = new RoomView(leaveRoomController, sendMessageController);

        // Call createPanel to get the JPanel
        JPanel panel = roomView.createPanel();
        assertNotNull(panel);

        // Test the existence of specific components
        assertTrue(hasComponentType(panel, JTextArea.class));
        assertTrue(hasComponentType(panel, JTextField.class));
        assertTrue(hasComponentType(panel, JButton.class));
        assertTrue(hasComponentType(panel, JScrollPane.class));
    }

    // Utility method to recursively search for a component with a specific type
    private boolean hasComponentType(Component component, Class<?> type) {
        if (type.isInstance(component)) {
            return true;
        }
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                if (hasComponentType(child, type)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Additional test methods can be added here to test specific functionalities
}
