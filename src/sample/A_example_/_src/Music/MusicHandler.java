/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Music;

import View.SettingsView;
import java.net.URL;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 *
 * @author Teddy
 */
public class MusicHandler {
    private ArrayList<URL> musicListURL;
    private MediaPlayer mediaplayer;
    private Media track;
    
    
    public MusicHandler()
    {
        musicListURL = new ArrayList();
        musicListURL.add(getClass().getResource("musicTypeA.mp3"));
        track = new Media(musicListURL.get(0).toString());
        mediaplayer = new MediaPlayer(track);
    }
    
    public void addMusicURL(String filename)
    {
        musicListURL.add(getClass().getResource(filename));
    }
    
    public void playMusic(int index)
    {
        if(mediaplayer.getStatus() == Status.PLAYING)
        {
            mediaplayer.stop();
        }
        
        track = new Media(musicListURL.get(index).toString());
        
        mediaplayer = new MediaPlayer(track);
        mediaplayer.play();

    }
    
    public void pauseMusic()
    {
        mediaplayer.pause();
    }
    
    public void stopMusic()
    {
        mediaplayer.stop();
    }
    
    public void resumeMusic()
    {
        if(mediaplayer.getStatus() == Status.PAUSED)
        {
            mediaplayer.play();
        }
    }
}
