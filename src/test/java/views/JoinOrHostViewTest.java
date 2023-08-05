package views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.host_room.HostRoomController;
import use_cases_and_adapters.join_room.JoinRoomController;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JoinOrHostViewTest {
    private HostRoomController hostRoomController;
    private JoinRoomController joinRoomController;
    private JoinOrHostView joinOrHostView;

    @BeforeEach
    void setUp() {
        hostRoomController = mock(HostRoomController.class);
        joinRoomController = mock(JoinRoomController.class);
        joinOrHostView = new JoinOrHostView(hostRoomController, joinRoomController);
    }

    @Test
    void testCreatePanel() {
        JPanel panel = joinOrHostView.createPanel();
        assertNotNull(panel);

        // Test the existence of specific components
        assertTrue(panel.getComponentCount() > 0);

        // Test the presence of JTextField, JButton, and JLabel components
        assertTrue(hasComponentType(panel, JTextField.class));
        assertTrue(hasComponentType(panel, JButton.class));
        assertTrue(hasComponentType(panel, JLabel.class));
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
    @Test
    void testCreateHostButton_ValidRoomName() {
        JButton hostButton = joinOrHostView.createHostButton();
        assertNotNull(hostButton);

        // Mocking user input for the room name text field
        joinOrHostView.roomField = new JTextField("TestRoomName");
        hostButton.doClick(); // Simulate a click on the "Host" button

        // Verify that the controller method is called with the correct argument
        verify(hostRoomController).handleHostRoom("TestRoomName");
    }

    @Test
    void testCreateHostButton_InvalidRoomName() {
        JButton hostButton = joinOrHostView.createHostButton();
        assertNotNull(hostButton);

        // Mocking user input for the room name text field
        joinOrHostView.roomField = new JTextField(""); // Invalid room name (empty)
        hostButton.doClick(); // Simulate a click on the "Host" button

        // Verify that the controller method is not called since the room name is invalid
        verify(hostRoomController, never()).handleHostRoom(any());
    }

    // Helper method to get components by their name in the panel
    private Component getComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (name.equals(component.getName())) {
                return component;
            }
            if (component instanceof Container) {
                Component found = getComponentByName((Container) component, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}