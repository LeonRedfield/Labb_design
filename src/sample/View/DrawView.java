package sample.View;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Teddy on 2017-03-08.
 */
public class DrawView extends CanvasView{
    private Scene scene;
    private VBox topContainer;
    private BorderPane rootPane;
    private Background stageBackground;
    private ToolBar toolBar;
    private Slider widthSlider;
    private Label widthSliderLabel;
    private ColorPicker colorPicker;
    private Button penButton, lineButton, ovalButton, polygonButton, cursorButton;
    private Canvas canvas;
    private GraphicsContext gc;

    public DrawView()
    {
        super();
        rootPane = new BorderPane();
        topContainer = new VBox();
        toolBar = new ToolBar();
        topContainer.getChildren().add(super.getAbstractMenubar());
        topContainer.getChildren().add(toolBar);
        rootPane.setTop(topContainer);

        //init toolmenu:

        //set Scene:
        scene = new Scene(rootPane, super.windowWidth, super.windowHeight);
        canvas = new Canvas(scene.getWidth(), scene.getHeight());
        gc = canvas.getGraphicsContext2D();
        rootPane.setStyle("-fx-background-color: white");
        //System.out.println("sceneH - topContainerH =="+scene.getHeight() + "+"+ topContainer.getLayoutY()+" = " + (scene.getHeight()-topContainer.getMaxHeight()) );
        initiateMenu();
        initCanvas();



        rootPane.setCenter(canvas);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Scene getScene()
    {
        return scene;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public ArrayList<MenuItem> getFileMenuItems() {
        return fileMenuItems;
    }

    public ArrayList<MenuItem> getEditMenuItems() {
        return editMenuItems;
    }

    public ArrayList<MenuItem> getViewMenuItems() {
        return viewMenuItems;
    }

    public void setCustomBackground(String imgPath)
    {
        javafx.scene.image.Image image = new javafx.scene.image.Image(imgPath);
        BackgroundSize backgroundSize = new BackgroundSize(windowWidth, windowHeight/5, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        this.rootPane.setBackground(background);
    }

    private void initiateMenu()
    {
        //items for toolbar:
        cursorButton = new Button();
        penButton = new Button();
        lineButton = new Button();
        ovalButton = new Button();
        polygonButton = new Button();
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        widthSlider = new Slider(1,10,1);
        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);
        widthSliderLabel = new Label("1.0");
        //set handlers:
        colorPicker.setOnAction(e->{
            gc.setStroke(colorPicker.getValue());
        });

        widthSlider.valueProperty().addListener(e->{
            double value = widthSlider.getValue();
            String valueStr = String.format("%.1f", value);
            widthSliderLabel.setText(valueStr);
            gc.setLineWidth(value*2);
        });

        //set icons to buttons:
        cursorButton.setGraphic(new ImageView("/sample/Resources/cursor.png"));
        penButton.setGraphic(new ImageView("/sample/Resources/pen.png"));
        lineButton.setGraphic(new ImageView("/sample/Resources/line.png"));
        ovalButton.setGraphic(new ImageView("/sample/Resources/oval.png"));
        polygonButton.setGraphic(new ImageView("/sample/Resources/polygon.png"));

        //add items to toolbar:
        toolBar.getItems().addAll(cursorButton, penButton,lineButton,ovalButton, polygonButton, widthSlider, widthSliderLabel,colorPicker);
    }

    private  void initCanvas()
    {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);


        canvas.setOnMousePressed(e->{
            gc.beginPath();
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(e->{
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });
    }
}
