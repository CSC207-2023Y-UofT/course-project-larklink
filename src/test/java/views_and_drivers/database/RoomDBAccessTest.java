package views_and_drivers.database;

import org.junit.jupiter.api.Test;
import use_cases_and_adapters.RoomDBModel;
import java.util.*;

class RoomDBAccessTest {

    // We omit the database methods because they are quite simple and are difficult to mock

    @Test
    void testRoomWrapperConstructor() {
        RoomDBModel roomDBModel = new RoomDBModel(1,"Test Room", 1, "1", "");
        RoomDBAccess.RoomWrapper roomWrapper = new RoomDBAccess.RoomWrapper(roomDBModel);
        assert roomWrapper.room == roomDBModel;
    }

    @Test
    void testRoomListWrapper() {
        RoomDBModel roomDBModel = new RoomDBModel(1,"Test Room", 1, "1", "");
        List<RoomDBModel> rooms = Collections.singletonList(roomDBModel);

        RoomDBAccess.RoomListWrapper roomListWrapper = new RoomDBAccess.RoomListWrapper();
        roomListWrapper.rooms = rooms;

        assert roomListWrapper.rooms.size() == 1;
        assert roomListWrapper.rooms.get(0) == roomDBModel;
    }
}
