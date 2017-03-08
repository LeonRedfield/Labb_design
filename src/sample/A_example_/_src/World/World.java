package sample.A_example_._src.World;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.stage.Stage;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
/**
 *
 * @author Teddy
 */

public class World extends Application{
    private static Stage window;
    private double windowWidth;
    private double windowHeight;
    private Menu menuFile, menuInstructions;
    private MenuItem itemStart, itemPause, itemResume, itemHowToPlay; 
    private MenuBar menubar;
    //tes

    
    public void start(Stage primaryStage){
        

        window  = primaryStage;
        setComputerScreenSize();
        Model model = new Model();
        ViewsList viewsList = new ViewsList(model);
        //Scene 0 - menu
        
        
        window.setTitle("Tetris");
        window.setScene(ViewsList.getCurrentViewScene());
        window.show();
    }

    
    public static Stage getWindow()
    {
        return window;
    }

    private void setComputerScreenSize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // takes computers size and 
        this.windowWidth = screenSize.getWidth()/1.5;
        this.windowHeight = screenSize.getHeight()/1.2;
        System.out.println("Screen width: " + windowWidth + "\n" + "Screen height: "+ windowHeight);
    }
    
    public static void main(String[] args) {       
        launch(args);
    }   
}
