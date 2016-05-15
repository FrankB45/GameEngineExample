package net.Andrewcpu.engine.utils.audio;

import javafx.scene.media.AudioClip;
import net.Andrewcpu.engine.utils.Log;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by stein on 5/14/2016.
 */
public class Sound {
    private boolean loop = false;
    private Clip clip;
    private double volume = 1;
    public Sound(String path){
        try {
            URL url = getClass().getResource(path);
            AudioInputStream ais;
            ais = AudioSystem.getAudioInputStream(url);
//            InputStream is= new BufferedInputStream(getClass().getResourceAsStream(path));
//
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
        clip.loop(loop == true ? Clip.LOOP_CONTINUOUSLY : 0);
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void start(){
        clip.setMicrosecondPosition(0);
        clip.start();
    }
    public void stop(){
        clip.stop();
    }

}
