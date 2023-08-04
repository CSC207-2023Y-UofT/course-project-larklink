package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is for testing Room entity.
 */
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

    /**
     * Tests setRoom method and getters.
     */
    @Test
    public void testSetRoom() {
        Room.setRoom(testRoomID, testRoomName, testHostID,
                testActiveUserIDs, testMessageHistory);

        // checks that the values are set correctly by comparing expected and actual values
        // also tests getters because we get actual values from getters
        assertEquals(testRoomID, Room.getRoomID());
        assertEquals(testRoomName, Room.getRoomName());
        assertEquals(testHostID, Room.getHostID());
        assertEquals(testActiveUserIDs, Room.getActiveUserIDs());
        assertEquals(testMessageHistory, Room.getMessageHistory());
    }
}
