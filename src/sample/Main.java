package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.View.CanvasView;
import sample.View.DrawView;

import java.awt.*;

public class Main extends Application {
    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        DrawView drawView = new DrawView();

        window  = primaryStage;
        window.setTitle("Ritprogram");
        window.setScene(drawView.getScene());
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
