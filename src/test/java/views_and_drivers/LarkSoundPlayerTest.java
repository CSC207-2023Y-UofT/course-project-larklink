package views_and_drivers;

import use_cases_and_adapters.messaging.LarkSoundPlayerGateway;
import org.junit.jupiter.api.*;

class LarkSoundPlayerTest {

// currently commented since this is not headless,
// but uncommented we reach 100% line coverage for this class
//    @Test
//    void testPlayLarkSound() {
//        // Provide the path to a valid audio file on your system
//        String validSoundFilePath = "/src/main/assets/lark_sound.wav";
//        LarkSoundPlayerGateway player = new LarkSoundPlayer(validSoundFilePath);
//        Assertions.assertDoesNotThrow(player::playLarkSound);
//    }

    @Test
    void testPlayLarkSoundWithNonExistentFile() {
        String invalidSoundFilePath = "/path/to/non/existent/file.wav";
        LarkSoundPlayerGateway player = new LarkSoundPlayer(invalidSoundFilePath);
        Assertions.assertThrows(RuntimeException.class, player::playLarkSound);
    }
}
