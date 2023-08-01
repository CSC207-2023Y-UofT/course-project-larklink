package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.converters.RoomConverter;
import host_room.HostRoomDBGateway;
import join_room.JoinByIDDBGateway;
import leave_room.LeaveRoomDBGateway;
import messaging.MessageDBGateway;

import javax.swing.*;

/**
 * This class provides a specific implementation of the DBAccess abstract class for RoomDBModel objects.
 * This class makes use of a RoomConverter for converting between RoomDBModel objects and their representation
 * in the database, and an HttpClient to make the actual HTTP requests.
 * The main operations this class provides are getting rooms, getting a single room by its ID, and updating a room.
 */
public class RoomDBAccess extends DBAccess<RoomDBModel> implements HostRoomDBGateway, JoinByIDDBGateway, LeaveRoomDBGateway, MessageDBGateway {

    public RoomDBAccess(HttpClient httpClient, RoomConverter converter) {
        super(httpClient, converter);
    }

    /**
     * Retrieves a list of all rooms from the database.
     * If an IOException occurs during the operation, we display an error message to the user, and an empty list is returned.
     *
     * @return a List of RoomDBModel objects representing all rooms in the database, or an empty list if an error occurred.
     */
    @Override
    public List<RoomDBModel> getRooms() {
        try {
            return getRows();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error connecting to the database!");
        }
        return new ArrayList<>();
    }

    /**
     * Retrieves a single room from the database based on its room ID.
     * If an IOException occurs during the operation, we display an error message to the user, and null is returned.
     *
     * @param roomID the ID of the room to retrieve.
     * @return a RoomDBModel object representing the room with the given ID, or null if an error occurred.
     */
    @Override
    public RoomDBModel getARoom(Integer roomID) {
        try {
            return getARow(roomID);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error connecting to the database!");
        }
        return null;
    }

    /**
     * Updates a single room in the database.
     * If an IOException occurs during the operation, we display an error message to the user.
     *
     * @param request a RoomDBModel object containing the updated data for the room.
     */
    @Override
    public void updateARoom(RoomDBModel request) {
        try {
            updateARow(request.getRoomID(), request);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error connecting to the database!");
        }
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