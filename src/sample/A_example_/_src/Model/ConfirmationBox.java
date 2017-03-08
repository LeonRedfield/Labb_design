/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Model;
import Controller.MenuBarController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
/**
 *
 * @author Teddy
 */
public class ConfirmationBox {
    
    static boolean answer;
    
    public static boolean showBox(String title, String textmessage)
    {
        Stage window = new Stage();
        //this will block interation with other windows until the current window is done.
        window.initModality(Modality.APPLICATION_MODAL); 
        window.setTitle(title);
        window.setMinWidth(200);
        window.setMinHeight(100);
        //create label for textmessage:
        Label label = new Label(title);
        label.setText(textmessage);
        //create option buttons:
        Button yesB = new Button("Yes");
        Button noB = new Button("No");
        
        yesB.setOnAction(e -> {
            answer = true;
            window.close();
        });
        
        noB.setOnAction(e -> {
            answer = false;
            window.close();
        });
        //create box for buttons:
        FlowPane bottomPane = new FlowPane();
        bottomPane.setHgap(10);
        bottomPane.setPadding(new Insets(5,5,5,5));
        bottomPane.getChildren().add(yesB);
        bottomPane.getChildren().add(noB);
        bottomPane.setAlignment(Pos.CENTER);
        //set nodes:
        BorderPane layout = new BorderPane();
        layout.setCenter(label);
        layout.setBottom(bottomPane);
        //create scene:
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
       
        return answer; 
    }
    
    public static boolean showPictureBox(String title, String textmessage, String picname)
    {
        Stage window = new Stage();
        //this will block interation with other windows until the current window is done.
        window.initModality(Modality.APPLICATION_MODAL); 
        window.setTitle(title);
        window.setMinWidth(1200);
        window.setMinHeight(800);
        //create label for textmessage:
        Label label = new Label(title);
        label.setText(textmessage);
        //create option buttons:
        Button yesB = new Button("Yes");
        
        yesB.setOnAction(e -> {
            answer = true;
            window.close();
        });
        
        //create box for buttons:
        FlowPane bottomPane = new FlowPane();
        bottomPane.setHgap(10);
        bottomPane.setPadding(new Insets(5,5,5,5));
        bottomPane.getChildren().add(yesB);
        bottomPane.setAlignment(Pos.CENTER);
        
        VBox picPane = new VBox();
        picPane.setMinSize(700, 600);
       //create pic as background:
        String imagepath;
        
        imagepath = MenuBarController.class.getResource(picname).toExternalForm();
        javafx.scene.image.Image image = new javafx.scene.image.Image(imagepath);
        BackgroundSize backgroundSize = new BackgroundSize(window.getWidth(), window.getHeight()/1.2, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background backgroud = new Background(backgroundImage);
        
        picPane.setBackground(backgroud);
                
        //set nodes:
        BorderPane layout = new BorderPane();
        layout.setTop(picPane);
        layout.setCenter(label);       
        layout.setBottom(bottomPane);
        //create scene:
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
       
        return answer; 
    }
    

}
