import javax.sound.sampled.*;
import java.io.*;

public class Sound {
    private Clip backgroundMusic;

    public void startBackgroundMusic() {
        try {
            File music = new File("sound/Music.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(music);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audio);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }
    
    public void soundEffect(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
