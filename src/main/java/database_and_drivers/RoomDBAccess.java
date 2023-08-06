package database_and_drivers;

import java.util.List;

import database_and_drivers.converters.RoomConverter;
import use_cases_and_adapters.RoomDBModel;
import use_cases_and_adapters.host_room.HostRoomDBGateway;
import use_cases_and_adapters.join_room.JoinRoomDBGateway;
import use_cases_and_adapters.leave_room.LeaveRoomDBGateway;
import use_cases_and_adapters.messaging.MessageDBGateway;

/**
 * The implementation of the DBAccess abstract class for RoomDBModel objects.
 */
public class RoomDBAccess extends DBAccess<RoomDBModel> implements HostRoomDBGateway, JoinRoomDBGateway, LeaveRoomDBGateway, MessageDBGateway {

    /**
     * Constructs a new RoomDBAccess object with the given HttpClient and RoomConverter instances.
     *
     * @param httpClient The HttpClient instance responsible for handling HTTP requests to the API.
     * @param converter  The UserConverter instance responsible for switching between JSON data to Room objects.
     */
    public RoomDBAccess(HttpClient httpClient, RoomConverter converter) {
        super(httpClient, converter);
    }

    /**
     * Retrieves a list of all rooms from the database.
     *
     * @return a List of RoomDBModel objects representing all rooms in the database, or an empty list if an error occurred.
     */
    @Override
    public List<RoomDBModel> getRooms() {
        return getRows();
    }

    /**
     * Retrieves a single room from the database based on its room ID.
     *
     * @param roomID the ID of the room to retrieve.
     * @return a RoomDBModel object representing the room with the given ID, or null if an error occurred.
     */
    @Override
    public RoomDBModel getARoom(Integer roomID) {
        return getARow(roomID);
    }

    /**
     * Updates a single room in the database.
     *
     * @param request a RoomDBModel object containing the updated data for the room.
     */
    @Override
    public void updateARoom(RoomDBModel request) {
        updateARow(request.getRoomID(), request);
    }

    /**
     * Returns the route for the Room database table.
     * This is used by the superclass methods to construct the URLs for the HTTP requests.
     *
     * @return the route for the Room database table.
     */
    @Override
    protected String getRoute() {
        return "rooms";
    }
}