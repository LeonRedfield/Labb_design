/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Controller;

import Model.Model;
import Music.MusicHandler;
import View.GamePlayView;
import View.HighScoreView;
import View.MainMenuView;
import View.PauseMenuView;
import View.SettingsView;
import View.Views;
import View.ViewsList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Teddy
 */
public class Controller {
    private static Stage rootwindow;
    private Model model;
    private Views views;
    private ViewsList viewslist;
    private KeyHandler keyhandler;
    private SettingsButtonHandler settingshandler;
    private Button[] buttons;
    private MusicHandler music;
    private MainMenuHandler mainmenuhandler;
    private GamePlayHandler gameplayhandler;
    private PauseHandler pausehandler;
    private Rectangle recttest;
    private static Scene gameplayScene;
    private HighScoreHandler highscorehandler;
    
    
    public Controller(Model model, Views view)
    {
        this.model = model;
        
        
        music = new MusicHandler();
    }
    /*
    public Controller(Rectangle block) //test rectangle
    {
        recttest = block;
        gameplayhandler = new GamePlayHandler();
    }

    public Controller(Button[] buttons)
    {
        //in generall every button handler comes through here:
        this.buttons = buttons;
        settingshandler = new SettingsButtonHandler();
        music = new MusicHandler();
        mainmenuhandler = new MainMenuHandler();
        pausehandler = new PauseHandler();
        //highscorehandler = new HighScoreHandler();
    }*/
    
    public KeyHandler getKeyHandler()
    {
        return keyhandler;
    }
    
    public SettingsButtonHandler getSettingsButtonHandler()
    {
        return settingshandler;
    }
    
    public MainMenuHandler getMainMenuHandler()
    {
        return mainmenuhandler;
    }
    
    public GamePlayHandler getGamePlayHandler()
    {
        return gameplayhandler;
    }
    
    public PauseHandler getPauseHandler()
    {
        return pausehandler;
    }
    
    public HighScoreHandler getHighScoreHandler()
    {
        return highscorehandler;
    }
    
    public void setRootWindow(Stage rootwindow)
    {
        this.rootwindow = rootwindow;
    }
    
    public void setGamePlayScene(Scene gameplay)
    {
        gameplayScene = gameplay;
    }
    
    public void setButtons(Button[] buttons)
    {
        this.buttons = buttons;
    }
     
    private class KeyHandler implements EventHandler<KeyEvent>
    {
        @Override
        public void handle(KeyEvent event) {
            
            switch(event.getCode())
            {
                case LEFT:
                    System.out.println("MOVE LEFT");
                    break;
                case RIGHT: System.out.println("MOVE RIGHT");
                    break;
                case UP:
                    System.out.println("ROTATE RIGHT");
                    break;
                case DOWN:
                    System.out.println("SOFT DROP");
                    break;
                case CONTROL:
                    System.out.println("ROTATE LEFT");
                    break;
                case SPACE:
                    System.out.println("HARD DROP");
                    break;
                case ESCAPE:
                    System.out.println("PAUSE");
                    break;
                default: 
                    
            }
        } 
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
               //rootwindow.setScene(viewsList.changeView(2));
           }
        }
        
    }
    
    private class MainMenuHandler implements EventHandler<KeyEvent>
    {
        
        @Override
        public void handle(KeyEvent event) 
        {
            
            System.out.println("got in mainmenuHandler");
            if(event.getSource() == buttons[0])
            {
                //Views nextView = new SettingsView();
//                rootwindow.setScene(nextView.getScene()); 
            }
            else if(event.getSource() == buttons[1])
            {
                System.out.println("VsCom");
            }
            else if(event.getSource() == buttons[2])
            {
                System.out.println("HighScore");
            }
            else if(event.getSource() == buttons[3])
            {               
                rootwindow.close();
            }
        }
        
    }
    
    private class GamePlayHandler implements EventHandler<KeyEvent>
    {

        @Override
        public void handle(KeyEvent event) 
        {
            switch(event.getCode())
            {
                case LEFT:
                    System.out.println("MOVE LEFT: X-pos -5");
                    
                    break;
                case RIGHT: System.out.println("MOVE RIGHT: X-pos +5");
                    
                    break;
                case UP:
                    System.out.println("ROTATE RIGHT");
                    break;
                case DOWN:
                    System.out.println("SOFT DROP");
                    break;
                case CONTROL:
                    System.out.println("ROTATE LEFT");
                    break;
                case SPACE:
                    System.out.println("HARD DROP");
                    break;
                case ESCAPE:
                    if(rootwindow == null)
                    {
                        System.out.println("no rootwindow. Execption here maybe");
                    }            
                    //Views nextView = new PauseMenuView();
                    //rootwindow.setScene(nextView.getScene());
                    System.out.println("PAUSE");
                    break;
                default:                     
            }         
        }        
    }
    
    private class PauseHandler implements EventHandler<KeyEvent>
    {

        @Override
        public void handle(KeyEvent event) 
        {
            if(event.getSource() == buttons[0])
            {
                rootwindow.setScene(gameplayScene); 
            }
            else if(event.getSource() == buttons[1])
            {
                System.out.println("Getting to Highscore");
                //Views nextScene = new HighScoreView();
                //rootwindow.setScene(nextScene.getScene());
            }
            else
            {
                System.out.println("Execption here maybe");
            }
        }       
    }
    
    private class HighScoreHandler implements EventHandler<ActionEvent>
    {
        
        @Override
        public void handle(ActionEvent event) 
        {
            System.out.println("Textfield should disappear and Scoreboard will show after the player has insert a name");

        }       
    }

       
}
