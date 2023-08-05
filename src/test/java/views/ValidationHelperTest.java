package views;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationHelperTest {

    @Test
    void testValidUsername() {
        assertTrue(ValidationHelper.isUsernameValid("john123"));
    }

    @Test
    void testInvalidUsername_Empty() {
        assertFalse(ValidationHelper.isUsernameValid(""));
    }

    @Test
    void testInvalidUsername_NonAlphanumeric() {
        assertFalse(ValidationHelper.isUsernameValid("john$%^"));
    }

    @Test
    void testInvalidUsername_Length() {
        assertFalse(ValidationHelper.isUsernameValid("jo"));
    }

    @Test
    void testValidPassword() {
        assertTrue(ValidationHelper.isPasswordValid("password123"));
    }

    @Test
    void testInvalidPassword_Empty() {
        assertFalse(ValidationHelper.isPasswordValid(""));
    }

    @Test
    void testInvalidPassword_NonAlphanumeric() {
        assertFalse(ValidationHelper.isPasswordValid("pass$%^"));
    }

    @Test
    void testInvalidPassword_Length() {
        assertFalse(ValidationHelper.isPasswordValid("pass123"));
    }

    @Test
    void testValidRepeatPassword() {
        assertTrue(ValidationHelper.isRepeatPasswordValid("password123", "password123"));
    }

    @Test
    void testInvalidRepeatPassword_Mismatch() {
        assertFalse(ValidationHelper.isRepeatPasswordValid("password123", "password456"));
    }

    @Test
    void testValidRoomName() {
        assertTrue(ValidationHelper.isRoomNameValid("room123"));
    }

    @Test
    void testInvalidRoomName_Empty() {
        assertFalse(ValidationHelper.isRoomNameValid(""));
    }

    @Test
    void testInvalidRoomName_NonAlphanumeric() {
        assertFalse(ValidationHelper.isRoomNameValid("room$%^"));
    }

    @Test
    void testInvalidRoomName_Length() {
        assertFalse(ValidationHelper.isRoomNameValid("room"));
    }
}
