package use_cases_and_adapters.messaging;

import use_cases_and_adapters.RoomDBModel;
import entities.Message;
import entities.Room;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MessageInteractorTest {
    @InjectMocks
    private MessageInteractor messageInteractor;

    @Mock
    private MessageOutputBoundary presenter;

    @Mock
    private MessageDBGateway database;

    @Mock
    private LarkSoundPlayerGateway larkSoundPlayer;

    private String testContent;
    private Integer hostID;
    private int userID;
    private int userID2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        messageInteractor = new MessageInteractor(database, presenter, larkSoundPlayer);
        messageInteractor = Mockito.spy(messageInteractor);

        //Initialize shared variables
        Integer roomID = 1;
        userID = 111;
        userID2 = 222;
        hostID = 2;
        testContent = "Hello";

        Room.setRoom(roomID, "Test Room", userID2, new ArrayList<>(Arrays.asList(userID, userID2)), ""); // simulate being in a room
        User.setUser(userID, "", ""); // simulate logging in a user
        RoomDBModel testRoom = new RoomDBModel(roomID, "Test Room", hostID,new ArrayList<>(Arrays.asList(userID, userID2)), "" );
        when(database.getARoom(roomID)).thenReturn(testRoom);
    }

    @Test
    public void testHandleSendMessage_emptyContent() {
        String emptyContent = "";
        messageInteractor.handleSendMessage(emptyContent);
        verify(presenter).prepareMessageErrorView();
        verify(database, never()).getARoom(anyInt());
        verify(database, never()).updateARoom(any(RoomDBModel.class));
    }

    @Test
    public void testHandleSendMessage_noLark() {
        Message msg = new Message(User.getUsername(), testContent);
        messageInteractor.handleSendMessage(testContent);
        ArgumentCaptor<RoomDBModel> captor = ArgumentCaptor.forClass(RoomDBModel.class);
        verify(database).updateARoom(captor.capture()); // check that we sent the message to the database
        RoomDBModel sentRoom = captor.getValue(); // get the argument that was passed to sendAMessage

        assertEquals(msg.getContent(), sentRoom.getMessageHistory());
        verify(presenter).prepareRoomView(sentRoom.getMessageHistory());
    }

    @Test
    public void testHandleSendMessage_withLarkSound() {
        String testContent = "Test message with /lark sound";
        messageInteractor.handleSendMessage(testContent);
        verify(larkSoundPlayer).playLarkSound();
    }

    @Test
    public void testHandleRetrieveMessages_noLark() {
        // Prepare the test data
        String messageHistory = "User1: Hello\nUser2: How are you?";
        RoomDBModel mockedRoom = new RoomDBModel(Room.getRoomID(), "Test Room", hostID, new ArrayList<>(Arrays.asList(userID, userID2)), messageHistory);
        when(database.getARoom(Room.getRoomID())).thenReturn(mockedRoom);

        // Call the method to be tested
        messageInteractor.handleRetrieveMessages();

        // Verify that the presenter method is called with the correct message history
        verify(presenter).prepareRoomView(messageHistory);

    }

    @Test
    public void testHandleRetrieveMessages_WithLark(){
        // Prepare the test data
        String messageHistory = "User1: Hello\nUser2: /lark";
        RoomDBModel mockedRoom = new RoomDBModel(Room.getRoomID(), "Test Room", hostID, new ArrayList<>(Arrays.asList(userID, userID2)), messageHistory);
        when(database.getARoom(Room.getRoomID())).thenReturn(mockedRoom);

        // Call the method to be tested
        messageInteractor.handleRetrieveMessages();

        // Verify that the presenter method is called with the correct message history
        verify(presenter).prepareRoomView(messageHistory);

        // Verify that the interactor calls the larkSoundPlayer to play the lark sound
        verify(larkSoundPlayer).playLarkSound();
    }
}