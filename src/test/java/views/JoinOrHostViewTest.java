package views;

import database_and_drivers.HttpClient;
import database_and_drivers.RoomDBAccess;
import database_and_drivers.converters.RoomConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import use_cases_and_adapters.host_room.HostRoomController;
import use_cases_and_adapters.host_room.HostRoomInputBoundary;
import use_cases_and_adapters.host_room.HostRoomPresenter;
import use_cases_and_adapters.join_room.JoinRoomController;
import use_cases_and_adapters.join_room.JoinRoomInteractor;
import use_cases_and_adapters.join_room.JoinRoomPresenter;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JoinOrHostViewTest {

    private static final String API_URL = "test";
    private JoinRoomPresenter joinRoomPresenter;
    @Mock
    private JoinRoomInteractor joinRoomInteractor;
    private JoinRoomController joinRoomController;
    private HostRoomController hostRoomController;
    private JoinOrHostView view;

    @Mock
    private RoomDBAccess roomDBAccess;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        HttpClient httpClient = new HttpClient(API_URL);
        RoomConverter roomConverter = new RoomConverter();

        roomDBAccess = new RoomDBAccess(httpClient, roomConverter);
        joinRoomPresenter = new JoinRoomPresenter();
        joinRoomInteractor = new JoinRoomInteractor(roomDBAccess, joinRoomPresenter);
        joinRoomController = new JoinRoomController(joinRoomInteractor);
        view = new JoinOrHostView(hostRoomController, joinRoomController);
    }

    @Test
    void testHostRoom() throws AWTException {
        view.prepareGUI(null);

        Robot bot = new Robot();
        // launch view
        bot.mouseMove(828, 435);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        bot.mouseMove(990, 580);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(joinRoomInteractor, times(1)).handleJoinRoom(any());
    }
}
