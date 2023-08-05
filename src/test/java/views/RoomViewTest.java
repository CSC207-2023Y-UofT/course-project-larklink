package views;

import org.junit.jupiter.api.Test;
import use_cases_and_adapters.leave_room.LeaveRoomController;
import use_cases_and_adapters.messaging.MessageController;

import javax.swing.*;
import static org.mockito.Mockito.mock;

class RoomViewTest {

    @Test
    void testCreatePanel() {
        LeaveRoomController leaveRoomController = mock(LeaveRoomController.class);
        MessageController sendMessageController = mock(MessageController.class);

        // create the room view
        RoomView roomView = new RoomView(leaveRoomController, sendMessageController);

        JPanel panel = roomView.createPanel();
        assert panel != null;

        // test for component gen
        assert ViewTestUtility.hasElement(panel, JTextArea.class);
        assert ViewTestUtility.hasElement(panel, JTextField.class);
        assert ViewTestUtility.hasElement(panel, JButton.class);
        assert ViewTestUtility.hasElement(panel, JScrollPane.class);
    }
}
