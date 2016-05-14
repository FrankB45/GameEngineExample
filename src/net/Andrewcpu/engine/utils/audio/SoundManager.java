package net.Andrewcpu.engine.utils.audio;

import net.Andrewcpu.engine.utils.Log;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.InputStream;

/**
 * Created by stein on 5/13/2016.
 */
public class SoundManager {
    public void playSound(String fileName)
    {
        try
        {
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        }
        catch (Exception e)
        {
            Log.d(e.getLocalizedMessage());
        }
    }
}
