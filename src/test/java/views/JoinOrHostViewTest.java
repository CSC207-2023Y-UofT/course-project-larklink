package views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.host_room.HostRoomController;
import use_cases_and_adapters.join_room.JoinRoomController;

import javax.swing.*;
import static org.mockito.ArgumentMatchers.any;
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
        assert panel != null;

        assert panel.getComponentCount() > 0;

        // Test constructing components
        assert ViewTestUtility.hasElement(panel, JTextField.class);
        assert ViewTestUtility.hasElement(panel, JButton.class);
        assert ViewTestUtility.hasElement(panel, JLabel.class);
        assert ViewTestUtility.hasElement(panel, JScrollPane.class);
    }

    @Test
    void testCreateHostButton_ValidRoomName() {
        JButton hostButton = joinOrHostView.createHostButton();
        assert hostButton != null;

        // Mocking user input for the room name text field
        joinOrHostView.roomField = new JTextField("roomname");
        hostButton.doClick();

        verify(hostRoomController).handleHostRoom("roomname");
    }

    @Test
    void testCreateHostButton_InvalidRoomName() {
        JButton hostButton = joinOrHostView.createHostButton();
        assert hostButton != null;

        // empty room name shouldn't work
        joinOrHostView.roomField = new JTextField("");
        hostButton.doClick();

        verify(hostRoomController, never()).handleHostRoom(any());
    }
}
