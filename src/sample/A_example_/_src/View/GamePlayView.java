/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.View;

import Controller.Controller;
import Controller.GamePlayController;
import Model.Model;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


/**
 *
 * @author Teddy
 */
public class GamePlayView extends Views{
    private BorderPane rootPane;
    private GridPane playField;
    private VBox ScoreandLevel;
    private Canvas[][] canvas;
    private Canvas[][] nextblockCanvas;
    private GamePlay gameplay;
    private GridPane nextblockPane;
    private Model model;
    private int[][] pixelplayfield;
    private int[][] pixelnextBlock;
    private VBox gameoverPane;
    private Button playagainYes;
    private Button playagainNo;
    private Label gameoverMessage;
    private GamePlayController keycontrol;
    private int scorePoint;
    private int Linesint;
    private Label score;
    private Label lines;
    
    public GamePlayView(Model model)
    {
        this.model = model;
        initView();
        rootPane.setTop(menubar);
    }
    
    public void startGamePlayTimer()
    {
        gameplay.start();
    }
    public void stopGamePlayTimer()
    {
        gameplay.stop();
    }
    
    public Button[] getButtons()
    {
        initiateNodes();
        Button[] buttons = {playagainYes, playagainNo};
        return buttons;
    }
    
    public void resetGame()
    {
        rootPane.getChildren().remove(0, rootPane.getChildren().size());
        model.reset();
        initView();
    }
    
    public void updateFromModel()
    {
          
        pixelplayfield = model.pixelGridToBeDrawn();
        pixelnextBlock = model.getNextBlockPixelGrid();
        model.modelUpdate();
        scorePoint = model.getScore();
        Linesint = model.getLines();
        score = new Label("Score: " + Integer.toString(scorePoint));
        lines = new Label("Lines: " + Integer.toString(Linesint));
        ScoreandLevel.getChildren().remove(0, ScoreandLevel.getChildren().size());
        ScoreandLevel.getChildren().addAll(score, lines);
    }
    
    
    private void initView()
    {
        scorePoint = 0;
        Linesint = 0;
        initiatePanes();
        scene = new Scene(rootPane, windowWidth, windowHeight);
        initiateNodes();
        setHandlers();
        gameplay = new GamePlay();
        gameplay.start();               
        gameoverPane.getChildren().addAll(gameoverMessage, playagainYes, playagainNo);
        gameoverPane.setAlignment(Pos.CENTER);

    }
    
    private void initiatePanes()
    {
        rootPane = new BorderPane();
        
        rootPane.setStyle("-fx-background-color: #ffa500;");
        playField = new GridPane();
        initiateCanvas();
        gameoverPane = new VBox(20);
        gameoverPane.setMaxSize(windowWidth/4, windowHeight/4);
        gameoverPane.setStyle("-fx-background-color: #c0c0c0;");
        //
        ScoreandLevel = new VBox();
        score = new Label("Score: " + Integer.toString(scorePoint));
        lines = new Label("Lines: " + Integer.toString(Linesint));
        ScoreandLevel.getChildren().addAll(score, lines);
        rootPane.setCenter(playField);
        rootPane.setRight(nextblockPane);
        rootPane.setLeft(ScoreandLevel);
    }
    
    private void initiateCanvas()
    {
        canvas = new Canvas[model.getRows()][model.getColumns()];
        for(int i=0 ; i< canvas.length; i++)
        {
            for(int j=0; j<canvas[0].length; j++)
            {
                canvas[i][j] = new Canvas();
                canvas[i][j].setWidth(40);
                canvas[i][j].setHeight(40);
                playField.add(canvas[i][j], j, i);
            }
        }
        playField.setMaxSize(canvas[0][0].getWidth()*canvas.length, canvas[0][0].getHeight()*canvas[0].length);
        
        //NextBlock pane:
        nextblockPane = new GridPane();
        nextblockCanvas = new Canvas[4][4];
        
        for(int i=0 ; i<4; i++)
        {
            for(int j=0; j<4; j++)
            {
                nextblockCanvas[i][j] = new Canvas();
                nextblockCanvas[i][j].setWidth(40);
                nextblockCanvas[i][j].setHeight(40);
                nextblockPane.add(nextblockCanvas[i][j], j, i);
            }
        }
        nextblockPane.setMaxSize(nextblockCanvas[0][0].getWidth()*nextblockCanvas.length, nextblockCanvas[0][0].getHeight()*nextblockCanvas[0].length);
    }
    
    private void initiateNodes()
    {
        playagainYes = new Button("YES");
        playagainNo = new Button("NO");
        gameoverMessage = new Label("Game Over!\nPlay Again?");
        gameoverMessage.setFont(Font.font(30));
    }
    
    private void setHandlers()
    {
        keycontrol = new GamePlayController(model, this);
        scene.setOnKeyReleased(keycontrol.getHandler());
        scene.setOnKeyPressed(keycontrol.getHandler());
        scene.setOnKeyTyped(keycontrol.getHandler());
        this.setMenuBarHandler(this);
    }
    
    public void removeHandler()
    {
        scene.setOnKeyReleased(null);
        scene.setOnKeyPressed(null);
        scene.setOnKeyTyped(null);
                
    }
    
    
    protected class GamePlay extends AnimationTimer
    {
        private long previousNs = 0;
        
        @Override
        public void handle(long nowNs) 
        {
            if(ViewsList.getCurrentViewIndex() == 2)
            {
                if (previousNs == 0) 
                {
                    previousNs = nowNs;
                }
                updateFromModel();
                paintCanvasGrid();
                
                if(model.isGameOver())
                {
                    gameplay.stop();
                    removeHandler();
                    rootPane.setCenter(gameoverPane);  
                    playagainYes.setOnKeyTyped(keycontrol.getButtonHandler());
                    playagainNo.setOnKeyTyped(keycontrol.getButtonHandler());             
                }
            }
        }  
    }
    
    private void paintCanvasGrid()
    {

        for(int i=2 ; i< pixelplayfield.length; i++)
            {
                for(int j=0; j<pixelplayfield[0].length; j++)
                {
                    GraphicsContext gc = canvas[i][j].getGraphicsContext2D();
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(0, 0, canvas[i][j].getWidth(), canvas[i][j].getHeight());
                    if(pixelplayfield[i][j] == 1)
                    {
                        gc.setFill(Color.YELLOW);
                        gc.fillRect(0, 0, canvas[i][j].getWidth(), canvas[i][j].getHeight());
                    }
                    else
                    {
                        gc.setFill(Color.ORANGE);
                        gc.fillRect(0, 0, canvas[i][j].getWidth(), canvas[i][j].getHeight());
                        gc.setStroke(Color.BLACK);
                        gc.strokeRect(0, 0, canvas[i][j].getWidth(), canvas[i][j].getHeight());
                    }                    
                }
            }
        
        for(int i=0; i< nextblockCanvas.length; i++)
        {
            for(int j=0; j< nextblockCanvas[0].length; j++)
            {
                GraphicsContext gc = nextblockCanvas[i][j].getGraphicsContext2D();
                gc.setFill(Color.ORANGE);
                gc.fillRect(0, 0, canvas[i][j].getWidth(), canvas[i][j].getHeight());
            }
        }
        
        for(int i=0 ; i<pixelnextBlock.length; i++)
        {
            for(int j=0; j<pixelnextBlock[0].length; j++)
            {
                GraphicsContext gc = nextblockCanvas[i][j].getGraphicsContext2D();
                gc.setStroke(Color.BLACK);
                gc.strokeRect(0, 0, canvas[i][j].getWidth(), canvas[i][j].getHeight());
                if(pixelnextBlock[i][j] == 1)
                {
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(0, 0, nextblockCanvas[i][j].getWidth(), nextblockCanvas[i][j].getHeight());
                }

            }
        }          
    }
    
}
