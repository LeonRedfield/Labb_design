/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Controller;

import Model.Model;
import Music.MusicHandler;
import View.SettingsView;
import View.Views;
import View.ViewsList;
import View.HighScoreView;
import World.World;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Teddy
 */
public class HighScoreController {
    private HighScoreView highscoreview;
    private Model model;
    private Button[] buttons;
    private MusicHandler music;
    private HighScoreHandler handler;  
    
    public HighScoreController(HighScoreView highscoreview)
    {
        this.highscoreview = highscoreview;
        handler = new HighScoreHandler();
    }
    
    public HighScoreHandler getHandler()
    {
        return handler;
    }
     private class HighScoreHandler implements EventHandler<ActionEvent> 
    {
        
        @Override
        public void handle(ActionEvent event) 
        {
            TextField enteredField = highscoreview.getPlayername();
            
           
            if(enteredField.getText() != null && enteredField.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Name field is empty");
                alert.setTitle("No name entered");
                alert.show();
                throw new NoNameEnteredException("No Name is entered");
            }
            else {
            ViewsList.resetViews();
            ViewsList.changeView(0);
            World.getWindow().setScene(ViewsList.getCurrentViewScene());
            event.consume();
            //Views mainScene = new MainMenuView();
            //rootwindow.setScene(mainScene.getScene());
            }
        }       
    }
}
