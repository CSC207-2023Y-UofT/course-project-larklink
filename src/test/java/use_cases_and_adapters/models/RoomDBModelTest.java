package use_cases_and_adapters.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases_and_adapters.RoomDBModel;

import java.util.List;

/**
 * This class tests RoomDBModel.
 */
public class RoomDBModelTest {
    private Integer id;
    private String roomName;
    private Integer hostId;
    private String messageHistory;
    private RoomDBModel testModel;

    @BeforeEach
    public void setUp() {
        id = 5;
        roomName = "LarkLink";
        hostId = 3;
        messageHistory = "[15:38:42] nadine: sup\n";
        testModel = new RoomDBModel(id, roomName, hostId, "3, 10", messageHistory);
    }

    /**
     * Test getRoomID method.
     */
    @Test
    public void testGetRoomID() {
        //check that expected value is equal to actual value from getRoomID
        assert id.equals(testModel.getRoomID());
    }

    /**
     * Test getRoomName method.
     */
    @Test
    public void testGetRoomName() {
        //check that expected value is equal to actual value from getRoomName
        assert roomName.equals(testModel.getRoomName());
    }

    /**
     * Test getHostID method.
     */
    @Test
    public void testGetHostID() {
        //check that expected value is equal to actual value from getHostID
        assert hostId.equals(testModel.getHostID());
    }

    /**
     * Test getActiveUserIDs method.
     */
    @Test
    public void testGetActiveUserIDs() {
        //check that expected list is equal to actual list from getActiveUserIDs
        List<Integer> expectedList = List.of(3, 10);
        assert expectedList.equals(testModel.getActiveUserIDs());
    }

    /**
     * Test getMessageHistory method.
     */
    @Test
    public void testGetMessageHistory() {
        //check that expected value is equal to actual value from getMessageHistory
        assert messageHistory.equals(testModel.getMessageHistory());
    }

    /**
     * Test setActiveUserIDs method.
     */
    @Test
    public void testSetActiveUserIDs() {
        List<Integer> newList = List.of(3, 10, 20);
        // set the activeUsers with added userID
        testModel.setActiveUserIDs(newList);
        //check that the roomModel is updated with new active users list
        assert newList.equals(testModel.getActiveUserIDs());
    }

    /**
     * Test setMessageHistory method.
     */
    @Test
    public void testSetMessageHistory() {
        String newMessageHistory = "[15:38:42] nadine: sup\n, [15:42:11] kim11: sup\n";
        // set the message history with updated message history
        testModel.setMessageHistory(newMessageHistory);
        //check that the roomModel is updated with new message history
        assert newMessageHistory.equals(testModel.getMessageHistory());
    }


}
