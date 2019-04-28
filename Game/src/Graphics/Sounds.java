package Graphics;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Sounds {

    public static void playSound(String path) {
        InputStream sound;
        try {
            sound = new FileInputStream(new File(path));
            AudioStream audios = new AudioStream(sound);
            AudioPlayer.player.start(audios);

        }catch(Exception e) {
            System.out.println("ERROR");
        }
    }
}