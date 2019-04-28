package Graphics;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Sounds {


    public static void playSound(File path) {
        try {
        	Clip clip = AudioSystem.getClip();
        	clip.open(AudioSystem.getAudioInputStream(path));
        	clip.start();
        }
        catch(Exception e) {
        }
    }
}