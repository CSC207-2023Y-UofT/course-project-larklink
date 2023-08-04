package views;

import javax.swing.*;
import java.util.regex.Pattern;

/**
 * A helper class to check the validation of username, room name, and password.
 */
public class ValidationHelper {

    /**
     * The regular expression pattern for valid username.
     */
    protected static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,}$");
    /**
     * The regular expression pattern for valid password.
     */
    protected static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{8,}$");
    /**
     * The regular expression pattern for valid room name.
     */
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{5,}$");


    /**
     * Validates a username based on given pattern.
     * Shows an error message and returns false if the username is empty.
     * Shows an error message and returns false if the username contains non-alphanumeric characters.
     * Shows an error message and returns false if the username length is less than 3.
     *
     * @param username the username entered by the user
     * @return true if the username is valid, otherwise false.
     */
    protected static boolean isUsernameValid(String username){
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Username field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!USERNAME_PATTERN.matcher(username).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Invalid username! Use only alphanumeric characters. Minimum length: 3",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Validates a password based on given pattern.
     * Shows an error message and returns false if the password is empty.
     * Shows an error message and returns false if the password contains non-alphanumeric characters.
     * Shows an error message and returns false if the password length is less than 8.
     *
     * @param password the password entered by the user
     * @return true if the password is valid, otherwise false.
     */
    protected static boolean isPasswordValid(String password){
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Password field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Invalid password! Use only alphanumeric characters. Minimum length: 8",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Validates a repeated password based on given pattern.
     * Shows an error message and returns false if the repeated password does not match the password.
     *
     * @param password the password entered by the user
     * @param repeatPassword the repeated password entered by the user
     * @return true if the repeated password is valid, otherwise false.
     */
    protected static boolean isRepeatPasswordValid(String password, String repeatPassword){
        if (!password.equals(repeatPassword)){
            JOptionPane.showMessageDialog(null,
                    "Repeat Password does not match.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Validates a room name based on given pattern.
     * Shows an error message and returns false if the room name is empty.
     * Shows an error message and returns false if the room name contains non-alphanumeric characters.
     * Shows an error message and returns false if the room name length is less than 5.
     *
     * @param roomName the room name entered by the user
     * @return true if the room name is valid, otherwise false.
     */
    protected static boolean isRoomNameValid(String roomName) {
        if (!NAME_PATTERN.matcher(roomName).matches()) {
            JOptionPane.showMessageDialog(null,
                    "Invalid Room name! Use only alphanumeric characters. Minimum length: 5",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (roomName.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Room Name is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

}
