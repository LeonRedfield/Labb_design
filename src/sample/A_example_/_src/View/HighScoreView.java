/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.View;

import Controller.Controller;
import Controller.HighScoreController;
import Model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Teddy
 */
public class HighScoreView extends Views{
    private BorderPane rootPane;
    private  TextField playername;
    private Button okB;
    private VBox bottomPane;
    private Scene ScoreBoard;
    private final Model model;
    private Label textlabel;
    
    public HighScoreView(Model model)
    {
        this.model = model;

        //instructions:
        System.out.println("Insert name and press ENTER to continue.");
        initiateNodes();
        //create rootPane:
        rootPane = new BorderPane();
        rootPane.setPadding(new Insets(10));
        rootPane.setStyle("-fx-background-color: #228b22;");
        
        setHandlers();
        initiatePanes();
        rootPane.setCenter(bottomPane);
        rootPane.centerProperty();
        //Scene 0 - menu
        rootPane.setTop(this.menubar);
        scene = new Scene(rootPane, windowWidth, windowHeight);
        playername.setFocusTraversable(true);
        playername.requestFocus();
    }
    
    public Button[] getButtons()
    {
        initiateNodes();
        Button[] buttons = {okB};
        return buttons;
    }
    
    private void setHandlers()
    {
        HighScoreController control = new HighScoreController(this);
        playername.setOnAction(control.getHandler());
        this.setMenuBarHandler(this);       
    }
    
    private void initiatePanes()
    {
        //create bottomPane:
        bottomPane = new VBox(20);
        bottomPane.getChildren().addAll(textlabel, playername);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setMaxSize(windowWidth/4, windowHeight/5);
        bottomPane.setStyle("-fx-background-color: #ffffff;");
    }
    
    private void initiateNodes()
    {
        playername = new TextField();
        textlabel = new Label("Please Insert Name");
        textlabel.setFont(Font.font(30));
        //okB = new Button("OK");
    }
    
    public TextField getPlayername() {
        return playername;
    }
}

