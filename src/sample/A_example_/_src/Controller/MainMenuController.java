/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Controller;

import Model.Model;
import View.MainMenuView;
import View.SettingsView;
import View.Views;
import View.ViewsList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Teddy
 */
public class MainMenuController {
    private Model model;
    private MainMenuView mainmenuview;
    private Button[] buttons;
    private MainMenuHandler handler;

    public MainMenuController(Model model, MainMenuView mainmenuview)
    {
        this.model = model;
        this.mainmenuview = mainmenuview;
        this.buttons = mainmenuview.getButtons();
        handler = new MainMenuHandler();
    }
    
    public MainMenuHandler getHandler()
    {
        return handler;
    }
    
    private class MainMenuHandler implements EventHandler<KeyEvent>
    {
        
        @Override
        public void handle(KeyEvent event) 
        {            
                
            if(event.getSource() == buttons[0])
                {
                    ViewsList.changeView(1);
                    World.getWindow().setScene(ViewsList.getCurrentViewScene()); 
                    
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
                    World.getWindow().close();
                }
                event.consume();
                
            
            
        }
        
    }
    
}
