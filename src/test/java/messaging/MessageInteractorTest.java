package messaging;

import database.RoomDBModel;
import entities.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class MessageInteractorTest {
    @InjectMocks
    private MessageInteractor messageInteractor;

    @Mock
    private MessageOutputBoundary presenter;

    @Mock
    private MessageDBGateway database;

    private String larkSoundFilePath;
    private String testContent;
    private String emptyContent;
    private Integer roomID;
    private Integer hostID;
    private int userID;
    private int userID2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        messageInteractor = new MessageInteractor(database, presenter, larkSoundFilePath);

        //Initialize shared variables
        roomID = 1;
        userID = 111;
        userID2 = 222;
        hostID = 2;
        testContent = "Hello";
        larkSoundFilePath = "/src/main/assets/lark_sound.wav";
        emptyContent = "";
        RoomDBModel testRoom = new RoomDBModel(roomID, "Test Room", hostID,new ArrayList<>(Arrays.asList(userID, userID2)), "" );
        when(database.getARoom(roomID)).thenReturn(testRoom);
    }

    @Test
    public void testHandleSendMessage_emptyContent() {
        messageInteractor.handleSendMessage(emptyContent);
        verify(presenter).prepareMessageErrorView();
        verify(database, never()).getARoom(anyInt());
        verify(database, never()).sendAMessage(any(RoomDBModel.class));
    }

    @Test
    public void testHandleSendMessage_noLarkSound() {
        messageInteractor.handleSendMessage(testContent);

        ArgumentCaptor<RoomDBModel> captor = ArgumentCaptor.forClass(RoomDBModel.class);
        verify(database).sendAMessage(captor.capture()); // check that we sent the message to the database
        RoomDBModel sentRoom = captor.getValue(); // get the argument that was passed to sendAMessage

        assertEquals("Old message\\n" + testContent, sentRoom.getMessageHistory());
        verify(presenter).prepareRoomView(sentRoom.getMessageHistory());
    }

    @Test
    public void testHandleSendMessage_withLarkSound() throws IOException {
        String testContent = "Test message with /lark sound";
        List<Integer> activeUserIDs = new ArrayList<>();
        activeUserIDs.add(1);
        RoomDBModel mockRoom = new RoomDBModel(Room.getRoomID(), "Test Room", 1, activeUserIDs, "");

        when(database.getARoom(Room.getRoomID())).thenReturn(mockRoom);

        messageInteractor.handleSendMessage(testContent);

        verify(database).getARoom(Room.getRoomID());
        verify(database).sendAMessage(any(RoomDBModel.class));
        verify(presenter).prepareRoomView(any());
        verify(messageInteractor).playLarkSound();
    }

    @Test
    public void testHandleRetrieveMessages_noLarkSound() {
        String testMessageHistory = "Test message history";
        List<Integer> activeUserIDs = new ArrayList<>();
        activeUserIDs.add(1);
        RoomDBModel mockRoom = new RoomDBModel(Room.getRoomID(), "Test Room", 1, activeUserIDs, testMessageHistory);

        when(database.getARoom(Room.getRoomID())).thenReturn(mockRoom);

        messageInteractor.handleRetrieveMessages();

        verify(database).getARoom(Room.getRoomID());
        verify(presenter).prepareRoomView(testMessageHistory);
    }

    @Test
    public void testHandleRetrieveMessages_withLarkSound() throws IOException {
        String testMessageHistory = "Test message history with /lark sound";
        List<Integer> activeUserIDs = new ArrayList<>();
        activeUserIDs.add(1);
        RoomDBModel mockRoom = new RoomDBModel(Room.getRoomID(), "Test Room", 1, activeUserIDs, testMessageHistory);

        when(database.getARoom(Room.getRoomID())).thenReturn(mockRoom);

        messageInteractor.handleRetrieveMessages();

        verify(database).getARoom(Room.getRoomID());
        verify(presenter).prepareRoomView(testMessageHistory);
        verify(messageInteractor).playLarkSound();
    }
}
