package database_and_drivers;

import java.util.List;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import use_cases_and_adapters.RoomDBModel;
import use_cases_and_adapters.host_room.HostRoomDBGateway;
import use_cases_and_adapters.join_room.JoinRoomDBGateway;
import use_cases_and_adapters.leave_room.LeaveRoomDBGateway;
import use_cases_and_adapters.messaging.MessageDBGateway;

/**
 * The implementation of the DBAccess abstract class for RoomDBModel objects.
 */
public class RoomDBAccess implements HostRoomDBGateway, JoinRoomDBGateway, LeaveRoomDBGateway, MessageDBGateway {
    private static final String ROUTE = "rooms";

    /**
     * Retrieves a list of all rooms from the database.
     *
     * @return a List of RoomDBModel objects representing all rooms in the database, or an empty list if an error occurred.
     */
    @Override
    public List<RoomDBModel> getRooms() {
        return Unirest.get(ROUTE).asObject(RoomResponseWrapper.class).getBody().rooms;
    }

    /**
     * Retrieves a single room from the database based on its room ID.
     *
     * @param roomID the ID of the room to retrieve.
     * @return a RoomDBModel object representing the room with the given ID, or null if an error occurred.
     */
    @Override
    public RoomDBModel getARoom(Integer roomID) {
        return Unirest.get(ROUTE + "/" + roomID).asObject(RoomDBModel.class).getBody();
    }

    /**
     * Updates a single room in the database.
     *
     * @param room a RoomDBModel object containing the updated data for the room.
     */
    @Override
    public void updateARoom(RoomDBModel room) {
        HttpResponse<String> response = Unirest.put(ROUTE + "/" + room.getRoomID())
                .header("Content-Type", "application/json")
                .body(new RoomRequestWrapper(room))
                .asString();
        System.out.println(response.getBody());
    }


    private static class RoomRequestWrapper {
        protected RoomDBModel room;
        public RoomRequestWrapper(RoomDBModel room) {
            this.room = room;
        }
    }

    private static class RoomResponseWrapper {
        protected List<RoomDBModel> rooms;
    }
}