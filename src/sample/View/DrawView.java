package sample.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import sample.Model.CircleShape;
import sample.Model.DrawDocument;
import sample.Model.Shape;
import sample.Model.SingleLine;

import java.util.ArrayList;

/**
 * Created by Teddy on 2017-03-08.
 */
public class DrawView extends CanvasView implements Observer{
    private Scene scene;
    private VBox topContainer;
    private BorderPane rootPane;
    private VBox centerPane;
    private Background stageBackground;
    private ToolBar toolBar;
    private Slider widthSlider;
    private Label widthSliderLabel;
    private ColorPicker colorPicker;
    private Button penButton, lineButton, ovalButton, polygonButton, cursorButton;
    private Canvas canvas;
    private GraphicsContext gc;

    private Shape currentShape;

    private DrawDocument drawDocument;

    public DrawView(DrawDocument drawDocument)
    {
        super();
        this.drawDocument = drawDocument;
        this.centerPane = new VBox();
        drawDocument.attach(this);

        rootPane = new BorderPane();
        topContainer = new VBox();
        toolBar = new ToolBar();
        topContainer.getChildren().add(super.getAbstractMenubar());
        topContainer.getChildren().add(toolBar);
        rootPane.setTop(topContainer);
        rootPane.setCenter(centerPane);
        //init toolmenu:

        //set Scene:
        scene = new Scene(rootPane, super.windowWidth, super.windowHeight);
        rootPane.setStyle("-fx-background-color: white");
        //centerPane.setStyle("-fx-background-color: yellow");
        centerPane.getChildren().add(new Line(10,10,0,0));
        //System.out.println("sceneH - topContainerH =="+scene.getHeight() + "+"+ topContainer.getLayoutY()+" = " + (scene.getHeight()-topContainer.getMaxHeight()) );
        initiateMenu();
        initCanvas();
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

        ovalButton.setOnAction(e->{
            currentShape = new CircleShape();
        });
        lineButton.setOnAction(e -> {
            currentShape = new SingleLine();
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


        centerPane.setOnMousePressed(e->{
            System.out.println("x: " +e.getX() +" y: " +e.getY());
            e.getX();
            e.getY();
            currentShape.setX(e.getX());
            currentShape.setY(e.getY());
        });

        centerPane.setOnMouseDragged(e->{
        });

        centerPane.setOnMouseReleased(e -> {
            System.out.println("x: " +e.getX() +" y: " +e.getY());
            System.out.println("Mouse released");
            currentShape.setEnd(e.getX(), e.getY());
            currentShape.setWidth(200);
            drawDocument.writeDrawData(currentShape);

            drawDocument.notifyAllObservers();
        });
        
    }

    @Override
    public void update() {
        System.out.println("update called by subject");
        Group group = new Group();
        for(Shape s: drawDocument.readDrawData()) {
            group.getChildren().add(s.draw());
        }
        centerPane.getChildren().remove(0,centerPane.getChildren().size());
        System.out.println("children antal: " + centerPane.getChildren().size());
        centerPane.getChildren().add(group);
    }
}
