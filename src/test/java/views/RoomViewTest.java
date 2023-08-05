package views;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import use_cases_and_adapters.leave_room.LeaveRoomController;
import use_cases_and_adapters.leave_room.LeaveRoomInputBoundary;
import use_cases_and_adapters.messaging.MessageController;
import use_cases_and_adapters.messaging.MessageInputBoundary;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class RoomViewTest {
    @Mock
    private LeaveRoomController leaveRoomController;
    @Mock
    private  LeaveRoomInputBoundary leaveRoomInputBoundary;
    @Mock
    private  MessageController sendMessageController;
    @Mock
    private MessageInputBoundary messageInputBoundary;
    private RoomView view;
    
    public RoomViewTest() {
        openMocks(this);
        leaveRoomController = new LeaveRoomController(leaveRoomInputBoundary);
        sendMessageController = new MessageController(messageInputBoundary);
        view = new RoomView(leaveRoomController, sendMessageController);
    }

    @Test
    public void testSendMessage() throws AWTException {
        view.prepareGUI(null);


        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Robot bot = new Robot();
        // launch view
        bot.mouseMove(828, 580);

        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);

        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        bot.keyPress(80);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bot.mouseMove(898, 580);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(messageInputBoundary, times(1)).handleSendMessage(any());

    }
}
