/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Controller;

import Model.Model;
import View.GamePlayView;
import View.PauseMenuView;
import View.Views;
import View.ViewsList;
import World.World;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Teddy
 */
public class GamePlayController {
    private Model model;
    private GamePlayView gameplayview;
    private Button[] buttons;
    private GamePlayHandler handler;
    private GamePlayButtonHandler buttonhandler;
    
    public GamePlayController(Model model, GamePlayView gameplayview) {
        this.model = model;
        this.gameplayview = gameplayview;
        handler = new GamePlayHandler();
        buttons = gameplayview.getButtons();
        buttonhandler = new GamePlayButtonHandler();
    }
    
    public GamePlayHandler getHandler()
    {
        return handler;
    }
    
    public GamePlayButtonHandler getButtonHandler()
    {
        return buttonhandler;
    }
    
    private class GamePlayHandler implements EventHandler<KeyEvent>
    {

        @Override
        public void handle(KeyEvent event) 
        {
            
                switch(event.getCode())
                {
                    case LEFT:
                        model.moveLeft();
                        break;
                    case RIGHT: 
                        model.moveRight();
                        break;
                    case UP:
                        model.rotateRight();
                        break;
                    case DOWN:
                        model.softDrop();
                        break;
                    case CONTROL:
                        model.rotateLeft();
                        break;
                    case SPACE:
                        model.hardDrop();
                        break;
                    case ESCAPE:                
                        ViewsList.changeView(3);
                        World.getWindow().setScene(ViewsList.getCurrentViewScene());              
                        break;
                    default:                     
                }
            event.consume();
        }        
    }
    
    private class GamePlayButtonHandler implements EventHandler<KeyEvent>
    {

        @Override
        public void handle(KeyEvent event) {
            
                if(event.getSource() == buttons[0])
                {
                    ViewsList.resetViews();
                    ViewsList.changeView(1);
                    World.getWindow().setScene(ViewsList.getCurrentViewScene()); 
                }
                else if(event.getSource() == buttons[1])
                {
                    ViewsList.changeView(4);
                    World.getWindow().setScene(ViewsList.getCurrentViewScene()); 
                }
        }       
    }
}
