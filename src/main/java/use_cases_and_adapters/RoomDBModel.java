package use_cases_and_adapters;

import java.util.List;

public class RoomDBModel {
    private final int roomID;
    private final String roomName;
    private final Integer hostID;
    private List<Integer> activeUserIDs;
    private String messageHistory;

    public RoomDBModel(int roomID, String roomName, Integer hostID, List<Integer> activeUserIDs, String messageHistory) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.hostID = hostID;
        this.activeUserIDs = activeUserIDs;
        this.messageHistory = messageHistory;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public Integer getHostID() {
        return this.hostID;
    }

    public List<Integer> getActiveUserIDs() {
        return this.activeUserIDs;
    }

    public String getMessageHistory() {
        return this.messageHistory;
    }

    public void setActiveUserIDs(List<Integer> activeUserIDs) {
        this.activeUserIDs = activeUserIDs;
    }

    public void setMessageHistory(String messageHistory) {
        this.messageHistory = messageHistory;
    }
}
