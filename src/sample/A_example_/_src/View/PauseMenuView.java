/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.View;

import Controller.Controller;
import Controller.PauseMenuController;
import Model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Teddy
 */
public class PauseMenuView extends Views{
    private Button resume;
    private VBox bottomPane;
    private Model model;
    
    public PauseMenuView(Model model)
    {
        this.model = model;
        //Initiate nodes:
        initiateNodes();
        //create rootPane:
        BorderPane rootPane = new BorderPane();
        rootPane.setPadding(new Insets(10));
        //set handler:
        setHandlers();
        //initiate panes:
        initiatePanes();
        
        rootPane.setCenter(bottomPane);
        rootPane.setTop(this.menubar);
        rootPane.setStyle("-fx-background-color: #ff0000;");
        //Scene 0 - menu
        scene = new Scene(rootPane, windowWidth, windowHeight);

    }
    
    public Button[] getButtons()
    {
        initiateNodes();
        Button[] buttons = {resume};
        return buttons;
    }
    
    private void setHandlers()
    {
        PauseMenuController control = new PauseMenuController(this);
        resume.setOnKeyTyped(control.getHandler()); 
        this.setMenuBarHandler(this);
    }
    
    private void initiatePanes()
    {
        //create bottomPane:
        bottomPane = new VBox(20);
        bottomPane.getChildren().addAll(resume);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setMinSize(windowWidth/5, windowHeight/4);
        bottomPane.setStyle("-fx-background-color: #ffffff;");
    }
    
    private void initiateNodes()
    {
        resume = new Button("RESUME");
    }
}
