/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Controller;

import Model.Model;
import View.GamePlayView;
import View.HighScoreView;
import View.PauseMenuView;
import View.Views;
import View.ViewsList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import World.World;
import javafx.scene.control.Button;

/**
 *
 * @author Teddy
 */
public class PauseMenuController {
    private PauseMenuView pauseview;
    private Button[] buttons;
    private PauseHandler handler;
    
    public PauseMenuController(PauseMenuView pauseview) {
        this.pauseview = pauseview;
        this.buttons = pauseview.getButtons();
        handler = new PauseHandler();
        
    }
    
    public PauseHandler getHandler()
    {
        return handler;
    }
    
    
    private class PauseHandler implements EventHandler<KeyEvent>
    {

        @Override
        public void handle(KeyEvent event) 
        {
            System.out.println("Keyinput: " + event.getCharacter());

                if(event.getSource() == buttons[0])
                {
                    System.out.println("Inne i PauseHandler");
                    ViewsList.changeView(2);
                    System.out.println(ViewsList.getCurrentViewIndex());
                    World.getWindow().setScene(ViewsList.getCurrentViewScene());
                    System.out.println("Returning to Gameplay\n");
                }
                else
                {
                    System.out.println("Execption here maybe");
                }
            event.consume();
            
            
            
        }       
    }
}
