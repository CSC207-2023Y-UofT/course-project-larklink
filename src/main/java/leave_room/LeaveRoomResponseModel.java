package leave_room;

/**
 * The response model for leaving a room action is represented by the LeaveRoomResponseModel class.
 * It stores details about the operation's success or failure as well as any error messages.
 */
public class LeaveRoomResponseModel {
    private boolean wasSuccess;
    private boolean errorMessage;

    /**
     * Constructs a new LeaveRoomResponseModel with the specified success and error message indicators.
     *
     * @param wasSuccess   A boolean indicating whether the leave room operation was successful.
     * @param errorMessage A boolean indicating whether there is an error message related to the operation.
     */
    public LeaveRoomResponseModel(boolean wasSuccess, boolean errorMessage) {
        this.wasSuccess = wasSuccess;
        this.errorMessage = errorMessage;
    }

    /**
     * Checks if the leave room operation was successful.
     *
     * @return true if the operation was successful, false otherwise.
     */
    public boolean wasSuccess() {
        return wasSuccess;
    }

    /**
     * Checks if there is an error message related to the leave room operation.
     *
     * @return true if there is an error message, false otherwise.
     */
    public boolean errorMessage() {
        return errorMessage;
    }
}
