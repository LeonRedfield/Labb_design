/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.View;

import Controller.Controller;
import Controller.MainMenuController;
import Controller.MenuBarController;
import Model.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Teddy
 */
public class MainMenuView extends Views{
    private VBox bottomPane;
    private Button newGameB;
    private Button exitB;
    private Button vsCom;
    private Button highscoreB;
    private Model model;
    private BorderPane rootPane;
    private Background stageBackground;
    
    public MainMenuView(Model model)
    {
        this.model = model;
        
        //create rootPane:
        
        rootPane = new BorderPane();
        //inititate nodes:
        InitiateNodes();
        //set handlers:
        setHandlers();
        //buttonPane:
        initiatePanes();
        //set background:
        rootPane.setBackground(getBackground(1));
        rootPane.setBottom(bottomPane);
        rootPane.setTop(this.menubar);
        //Scene 0 - menu
        
        scene = new Scene(rootPane, windowWidth, windowHeight);
    } 
    
    private void initiatePanes()
    {
        bottomPane = new VBox(20);
        bottomPane.getChildren().addAll(newGameB, vsCom, highscoreB, exitB);
        bottomPane.setMinSize(windowWidth/5, windowHeight/4);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setStyle("-fx-background-color: #ffffff;");
    }
    
    private void InitiateNodes()
    {
        newGameB = new Button("NEW GAME");
        vsCom =  new Button("VS.COM");
        highscoreB = new Button ("HIGHSCORE");
        exitB = new Button("EXIT");
    }
    
    private Background getBackground(int index)
    {
        String imagepath;
        Background backgroud;
        if(index == 1)
        {
            imagepath = MainMenuView.class.getResource("menupic.PNG").toExternalForm();
        }
        else
        {
            imagepath = MainMenuView.class.getResource("button.png").toExternalForm();
        }
        Image image = new Image(imagepath);
        BackgroundSize backgroundSize = new BackgroundSize(windowWidth, windowHeight/5, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        backgroud = new Background(backgroundImage);
        
        return backgroud;
    }
    
    
    public Button[] getButtons()
    {
        InitiateNodes();
        Button[] buttons = {newGameB, vsCom, highscoreB, exitB};
        return buttons;
    }
    
    
    private void setHandlers()
    {
        MainMenuController control = new MainMenuController(model, this);
        newGameB.setOnKeyTyped(control.getHandler());
        vsCom.setOnKeyTyped(control.getHandler());
        highscoreB.setOnKeyTyped(control.getHandler());
        exitB.setOnKeyTyped(control.getHandler());
        MenuBarController menucontrol = new MenuBarController(this);
        this.setMenuBarHandler(this);
        //MenuBarController menubarcontrol = new MenuBarController(this, getMenuBar());
    }
}
