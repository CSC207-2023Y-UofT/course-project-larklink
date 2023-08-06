package use_cases_and_adapters.host_room;

import use_cases_and_adapters.RoomDBModel;

import java.util.List;

public interface HostRoomDBGateway {
    /**
     * Fetches a complete list of the remotely stored rooms
     * @return A list of the remotely stored rooms
     */
    List<RoomDBModel> getRooms();

    /**
     * Adds a new room to the RoomDB
     * @param request a RoomDBModel containing information about the new room
     *                to be sent to the database
     */
    void updateARoom(RoomDBModel request);
}