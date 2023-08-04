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
     * Tests setRoom method.
     */
    @Test
    public void testSetRoom() {
        Room.setRoom(testRoomID, testRoomName, testHostID,
                testActiveUserIDs, testMessageHistory);

        // checks that the values are set correctly by comparing expected and actual values
        assertEquals(testRoomID, Room.getRoomID());
        assertEquals(testRoomName, Room.getRoomName());
        assertEquals(testHostID, Room.getHostID());
        assertEquals(testActiveUserIDs, Room.getActiveUserIDs());
        assertEquals(testMessageHistory, Room.getMessageHistory());
    }

    /**
     * Tests getRoomID method.
     */
    @Test
    public void testGetRoomID() {
        Room.setRoom(testRoomID, testRoomName, testHostID,
                testActiveUserIDs, testMessageHistory);
        // checks that expected value is equal to actual value from getRoomID
        assertEquals(testRoomID, Room.getRoomID());
    }

    /**
     * Tests getRoomName method.
     */
    @Test
    public void testGetRoomName() {
        Room.setRoom(testRoomID, testRoomName, testHostID,
                testActiveUserIDs, testMessageHistory);
        // checks that expected value is equal to actual value from getRoomName
        assertEquals(testRoomName, Room.getRoomName());
    }

    /**
     * Tests getHostID method.
     */
    @Test
    public void testGetHostID() {
        Room.setRoom(testRoomID, testRoomName, testHostID,
                testActiveUserIDs, testMessageHistory);
        // checks that expected value is equal to actual value from getHostID
        assertEquals(testHostID, Room.getHostID());
    }

    /**
     * Tests getActiveUserIDs method.
     */
    @Test
    public void testGetActiveUserIDs() {
        Room.setRoom(testRoomID, testRoomName, testHostID,
                testActiveUserIDs, testMessageHistory);
        // checks that expected value is equal to actual value from getActiveUserIDs
        assertEquals(testActiveUserIDs, Room.getActiveUserIDs());
    }

    /**
     * Tests getMessageHistory method.
     */
    @Test
    public void testGetMessageHistory() {
        Room.setRoom(testRoomID, testRoomName, testHostID,
                testActiveUserIDs, testMessageHistory);
        // checks that expected value is equal to actual value from getMessageHistory
        assertEquals(testMessageHistory, Room.getMessageHistory());
    }

}
