package use_cases_and_adapters.messaging;

/**
 * This class is interface that allows the interactor to play the Lark sound.
 */
public interface LarkSoundPlayerGateway {

    /**
     * Plays the lark sound.
     * This method attempts to open and play the sound file specified in the constructor.
     * If the file is not found or there is an issue playing the sound, a RuntimeException will be thrown.
     *
     * @throws RuntimeException if there is an error playing the sound.
     */
    void playLarkSound();
}
