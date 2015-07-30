package org.tmd.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

/**
 *
 * @author yew_mentzaki
 */
public class Sounds {

    public static float music = 1, sound = 1;
    public static Music musicAudio;
    private static Sound[] sounds;

    private static class Sound {

        Audio[] a;
        String name;

        public Sound(String name) {
            this.name = name;
            File dir = new File("res/sounds/" + name);
            File[] f = dir.listFiles();
            a = new Audio[f.length];
            for (int i = 0; i < f.length; i++) {
                try {
                    a[i] = AudioLoader.getAudio("OGG", new FileInputStream(f[i]));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void play() {
            a[Main.RANDOM.nextInt(a.length)].playAsSoundEffect(1, sound, false);
        }
    }

    public static void startBackgroundLoad() {
        Thread t = new Thread() {

            @Override
            public void run() {
                backgroundLoad();
            }

        };
        t.start();
    }

    private static void backgroundLoad() {
        try {
            musicAudio = new Music(new FileInputStream(new File("res/music/New_Day.ogg")), ".ogg");
            musicAudio.setVolume(music);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SlickException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
        File[] f = new File("res/sounds").listFiles();
        sounds = new Sound[f.length];
        for (int i = 0; i < f.length; i++) {
            sounds[i] = new Sound(f[i].getName());
        }
    }

    public static void play(String sound) {
        for (Sound s : sounds) {
            if (s.name.equals(sound)) {
                s.play();
                return;
            }
        }
    }
}
