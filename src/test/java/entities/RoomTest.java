package entities;

import org.junit.jupiter.api.*;
import java.util.*;

public class RoomTest {
    private Integer testRoomID;
    private String testRoomName;
    private Integer testHostID;
    private List<Integer> testActiveUserIDs;
    private String testMessageHistory;

    @BeforeEach
    public void setUp() {
        testRoomID = 70;
        testRoomName = "LarkRoom";
        testHostID = 350;
        testActiveUserIDs = new ArrayList<>(List.of(testHostID));
        testMessageHistory = "hi";
    }

    @Test
    public void testSetRoom() {
        Room.setRoom(testRoomID, testRoomName, testHostID, testActiveUserIDs, testMessageHistory);
        assert Room.getRoomID().equals(testRoomID);
        assert Room.getRoomName().equals(testRoomName);
        assert Room.getHostID().equals(testHostID);
        assert Room.getActiveUserIDs().equals(testActiveUserIDs);
        assert Room.getMessageHistory().equals(testMessageHistory);
    }
}
