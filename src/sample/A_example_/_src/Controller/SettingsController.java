/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Controller;

import Model.Model;
import Music.MusicHandler;
import View.SettingsView;
import View.ViewsList;
import World.World;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Teddy
 */
public class SettingsController {
    private SettingsView settingsview;
    private Model model;
    private Button[] buttons;
    private MusicHandler music;
    private SettingsButtonHandler handler;
    private ViewsList viewList;
    
    public SettingsController(Model model, SettingsView settingsview)
    {
        this.model = model;
        this.settingsview = settingsview;
        this.buttons = settingsview.getButtons();
        music = new MusicHandler();
        handler = new SettingsButtonHandler();
    }
    
    public SettingsButtonHandler gethandler()
    {
        return handler;
    }
    
    
    private class SettingsButtonHandler implements EventHandler<KeyEvent>
    {
        
        @Override
        public void handle(KeyEvent event) {
            
           if(event.getSource() == buttons[0])
           {
               music.playMusic(0);
           }
           else if(event.getSource() == buttons[1])
           {
               music.playMusic(0);
           }
           else if(event.getSource() == buttons[2])
           {
               music.playMusic(0);
           }
           else if(event.getSource() == buttons[3])
           {
               music.stopMusic();
           }
           else if(event.getSource() == buttons[4])
           {
                ViewsList.changeView(2);
                World.getWindow().setScene(ViewsList.getCurrentViewScene());
                
           }
           event.consume();
        }
        
    }
}
