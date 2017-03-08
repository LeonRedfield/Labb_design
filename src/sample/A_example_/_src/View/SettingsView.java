/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.View;


import Controller.Controller;
import Controller.SettingsController;
import Model.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Teddy
 */
public class SettingsView extends Views{
    private Background stageBackground;
    private Model model;
    private Button music1;
    private Button music2;
    private Button music3;
    private Button musicoff;
    private Button start;
    //Panes:
    private Label musictitle;
    private VBox bottomPane; 
    
    public SettingsView(Model model)
    {
        this.model = model;    
        //initiate nodes:
        InitiateNodes();
        //create rootPane:
        BorderPane rootPane = new BorderPane();
        //set buttonhandler:
        setHandlers();
        //initate Panes:
        initiatePanes();
        //add nodes:
        rootPane.setBottom(bottomPane);
           
        //test backgrund, to remove later if necesary:
        initiateBackground();
        rootPane.setBackground(stageBackground);
        rootPane.setTop(this.menubar);
        //Scene 0 - menu
        scene = new Scene(rootPane, windowWidth, windowHeight);
    } 
    
    public Button[] getButtons()
    {
        InitiateNodes();
        Button[] buttons = {music1,music2,music3, musicoff, start};
        return buttons;
    }
    
    private void initiateBackground()
    {
        String imagepath = SettingsView.class.getResource("tetrisBackground.png").toExternalForm();
        Image image = new Image(imagepath);
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        stageBackground = new Background(backgroundImage);
    }
    
    private void initiatePanes()
    {
        //initate musicPane:
        VBox musicPane = new VBox(20);
        musicPane.setMinHeight(windowHeight/3);
        musicPane.setMaxWidth(windowWidth/4);
        musicPane.getChildren().addAll(music1, music2, music3, musicoff, start);
        musicPane.setAlignment(Pos.CENTER);
        musicPane.setStyle( "-fx-border-color: khaki;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n"
                + "-fx-background-color: #000000"   //color black
                ); //color black
        
        //initiate titlePane:
        VBox titlebottomPane = new VBox(20);
        titlebottomPane.setMaxWidth(windowWidth/4);
        titlebottomPane.getChildren().add(musictitle);
        titlebottomPane.setAlignment(Pos.CENTER);
        titlebottomPane.setStyle( "-fx-border-color: khaki;\n"
                + "-fx-border-insets: 1;\n"
                + "-fx-border-width: 5;\n"
                + "-fx-background-color: #000000"   //color black
                );
        
        //create bottomPane:
        bottomPane = new VBox(20);
        
        bottomPane.getChildren().addAll(titlebottomPane, musicPane);
        bottomPane.setAlignment(Pos.CENTER);
    }
    
    private void setHandlers()
    {
        SettingsController control = new SettingsController(model, this);
        
        music1.setOnKeyTyped(control.gethandler());
        music2.setOnKeyTyped(control.gethandler());
        music3.setOnKeyTyped(control.gethandler());
        musicoff.setOnKeyTyped(control.gethandler());
        start.setOnKeyTyped(control.gethandler());
        this.setMenuBarHandler(this);
    }
    
    private void InitiateNodes()
    {
        music1 = new Button("MUSIC - 1");
        music2 = new Button("MUSIC - 2");
        music3 = new Button("MUSIC - 3");
        musicoff = new Button("OFF");
        start = new Button("START");
        musictitle = new Label("MUSIC TYPE");
        musictitle.setTextFill(Color.WHITE);
        musictitle.setFont(Font.font(30));
    }
    
}
