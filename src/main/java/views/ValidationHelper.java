package views;

import javax.swing.*;
import java.util.regex.Pattern;

/**
 * A helper class to check the validation of username, room name, and password.
 */
public class ValidationHelper {
    protected static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,}$");
    protected static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{8,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{5,}$");


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

    protected static boolean isRepeatPasswordValid(String password, String repeatPassword){
        if (!password.equals(repeatPassword)){
            JOptionPane.showMessageDialog(null,
                    "Repeat Password does not match.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

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
