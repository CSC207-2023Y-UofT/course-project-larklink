package database_and_drivers;

import use_cases_and_adapters.messaging.LarkSoundPlayerGateway;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Implementation of the LarkSoundPlayerGateway that plays a specified sound file.
 */
public class LarkSoundPlayer implements LarkSoundPlayerGateway {
    private final String soundFilePath;

    /**
     * Constructor for LarkSoundPlayer.
     *
     * @param soundFilePath The relative path to the sound file to be played.
     */
    public LarkSoundPlayer(String soundFilePath) {
        this.soundFilePath = soundFilePath;
    }

    /**
     * Plays the lark sound.
     * This method attempts to open and play the sound file specified in the constructor.
     * If the file is not found or there is an issue playing the sound, a RuntimeException will be thrown.
     *
     * @throws RuntimeException if there is an error playing the sound.
     */
    @Override
    public void playLarkSound() {
        String userDir = System.getProperty("user.dir");
        File soundFile = new File(userDir + soundFilePath);

        // notice "try with resources" means that the input stream will be closed automatically
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException("Error playing sound", e);
        }
    }
}