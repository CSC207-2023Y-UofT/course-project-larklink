package views;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for ValidationHelper.
 */
public class ValidationHelperTest {

    /**
     * Tests isUsernameValid for an empty username.
     * Checks that isUsernameValid returns false.
     */
    @Test
    public void testIsUsernameValid_EmptyUserName() {
        assertFalse(ValidationHelper.isUsernameValid(""));
    }

    /**
     * Tests isUsernameValid for the username with non-alphanumeric characters.
     * Checks that isUsernameValid returns false.
     */
    @Test
    public void testIsUsernameValid_NonAlphanumericCharacter() {
        assertFalse(ValidationHelper.isUsernameValid("name@@@###"));
    }

    /**
     * Tests isUsernameValid when the length of the username is less than 3.
     * Checks that isUsernameValid returns false.
     */
    @Test
    public void testIsUsernameValid_ShorterThanMinimumLength() {
        assertFalse(ValidationHelper.isUsernameValid("la"));
    }

    /**
     * Tests isUsernameValid for the valid username.
     * Checks that isUsernameValid returns true.
     */
    @Test
    public void testIsUsernameValid_ValidUserName() {
        assertTrue(ValidationHelper.isUsernameValid("Java"));
    }

    /**
     * Tests isPasswordValid for an empty password.
     * Checks that isPasswordValid returns false.
     */
    @Test
    public void testIsPasswordValid_EmptyPassword() {
        assertFalse(ValidationHelper.isPasswordValid(""));
    }

    /**
     * Tests isPasswordValid for the password with non-alphanumeric characters.
     * Checks that isPasswordValid returns false.
     */
    @Test
    public void testIsPasswordValid_NonAlphanumericCharacter() {
        assertFalse(ValidationHelper.isPasswordValid("pw123$$$"));
    }

    /**
     * Tests isPasswordValid when the length of the password is less than 8.
     * Checks that isPasswordValid returns false.
     */
    @Test
    public void testIsPasswordValid_ShorterThanMinimumLength() {
        assertFalse(ValidationHelper.isPasswordValid("1234567"));
    }

    /**
     * Tests isPasswordValid for the valid password.
     * Checks that isPasswordValid returns true.
     */
    @Test
    public void testIsPasswordValid_ValidPassword() {
        assertTrue(ValidationHelper.isPasswordValid("myPW1234"));
    }

    /**
     * Tests isRepeatPasswordValid for matching passwords.
     * Checks that isRepeatPasswordValid returns true.
     */
    @Test
    public void testIsRepeatPasswordValid_ValidRepeatPassword() {
        assertTrue(ValidationHelper.isRepeatPasswordValid("12345678", "12345678"));
    }

    /**
     * Tests isRepeatPasswordValid for different passwords.
     * Checks that isRepeatPasswordValid returns false.
     */
    @Test
    public void testIsRepeatPasswordValid_InValidRepeatPassword() {
        assertFalse(ValidationHelper.isRepeatPasswordValid("myPW1234", "12345678"));
    }

    /**
     * Tests isRoomNameValid for an empty room name.
     * Checks that isRoomNameValid returns false.
     */
    @Test
    public void testIsRoomNameValid_EmptyRoomName() {
        assertFalse(ValidationHelper.isRoomNameValid(""));
    }

    /**
     * Tests isRoomNameValid for the room name with non-alphanumeric characters.
     * Checks that isRoomNameValid returns false.
     */
    @Test
    public void testIsRoomNameValid_NonAlphanumericCharacter() {
        assertFalse(ValidationHelper.isRoomNameValid("It's my room"));
    }

    /**
     * Tests isRoomNameValid when the length of room name is less than 5.
     * Checks that isRoomNameValid returns false.
     */
    @Test
    public void testIsRoomNameValid_ShorterThanMinimumLength() {
        assertFalse(ValidationHelper.isRoomNameValid("room"));
    }

    /**
     * Tests isRoomNameValid for the valid room name.
     * Checks that isRoomNameValid returns true.
     */
    @Test
    public void testIsRoomNameValid_ValidRoomName() {
        assertTrue(ValidationHelper.isRoomNameValid("LarkLink"));
    }
}
