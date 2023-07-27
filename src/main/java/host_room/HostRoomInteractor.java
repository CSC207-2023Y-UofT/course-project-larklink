package host_room;

import database.RoomDBGateway;
import models.RoomDBModel;
import models.RoomModel;

import java.util.List;

public class HostRoomInteractor implements HostRoomInputBoundary{
    private final RoomDBGateway database;
    private final HostRoomOutputBoundary presenter;

    public HostRoomInteractor(RoomDBGateway database, HostRoomOutputBoundary presenter) {
        this.database = database;
        this.presenter = presenter;
    }

    /**
     * Creates a new room and stores it in the database.
     * If a room is found with the same host, then the room is not created and a duplicateHostView is prepared
     *
     * @param request the room model containing the host, active users, and roomID of the room to be created
     */

    public void hostRoom(RoomModel request) {
        List<RoomDBModel> existingRooms = database.retrieveEveryRoom();

        for (RoomDBModel existingRoom : existingRooms) {
            // user already hosting room
            if (existingRoom.getHost().equals(request.getHost())) {
                presenter.prepareMultipleHostingView();
                return;
            }
        }

        RoomDBModel newRoom = new RoomDBModel(existingRooms.size() + 2, request.getActiveUsers(),
                request.getHost(), request.getName());
        database.addARoom(newRoom);
        presenter.prepareRoomView(newRoom.getHost());
    }
}
