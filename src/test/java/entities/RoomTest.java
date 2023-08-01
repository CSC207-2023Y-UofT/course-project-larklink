package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Room.setRoom(testRoomID, testRoomName, testHostID,
                testActiveUserIDs, testMessageHistory);

        assertEquals(testRoomID, Room.getRoomID());
        assertEquals(testRoomName, Room.getRoomName());
        assertEquals(testHostID, Room.getHostID());
        assertEquals(testActiveUserIDs, Room.getActiveUserIDs());
        assertEquals(testMessageHistory, Room.getMessageHistory());
    }
}
