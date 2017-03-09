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
import sample.Model.*;

import java.util.ArrayList;
import java.util.List;

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
    private Button penButton, lineButton, ovalButton, rectangleButton, cursorButton;
    private CheckBox fillCheckBox;
    private Canvas canvas;
    private GraphicsContext gc;

    private Shape currentShape;

    private DrawDocument drawDocument;
    private UndoMenu undoMenu;
    private RedoMenu redoMenu;

    public DrawView(DrawDocument drawDocument)
    {
        super();
        this.drawDocument = drawDocument;
        undoMenu = new UndoMenu();
        redoMenu = new RedoMenu();

        this.centerPane = new VBox();

        drawDocument.attach(this);

        rootPane = new BorderPane();
        topContainer = new VBox();
        toolBar = new ToolBar();
        topContainer.getChildren().add(super.getAbstractMenubar());
        topContainer.getChildren().add(toolBar);
        this.centerPane.setMaxSize(400, 400);
        rootPane.setTop(topContainer);
        rootPane.setCenter(centerPane);

        //init toolmenu:

        //set Scene:
        scene = new Scene(rootPane, super.windowWidth, super.windowHeight);
        rootPane.setStyle("-fx-background-color: white");
        centerPane.setStyle("-fx-background-color: yellow");
        centerPane.getChildren().add(new Line(10,10,0,0));
        //System.out.println("sceneH - topContainerH =="+scene.getHeight() + "+"+ topContainer.getLayoutY()+" = " + (scene.getHeight()-topContainer.getMaxHeight()) );
        initiateMenu();

        //Init undo/redo menu item handlers
        //(Undo)
        editMenuItems.get(0).setOnAction(e -> {
            if(!undoMenu.isEmpty()){
                EditCommand c = undoMenu.pop();
                c.execute();
                redoMenu.push(c);
            }

        });
        editMenuItems.get(1).setOnAction(e -> {
            if(!redoMenu.isEmpty()) {
                EditCommand c = undoMenu.pop();
                c.execute();
                redoMenu.push(c);
            }
        });


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
        fillCheckBox = new CheckBox("Fill");
        cursorButton = new Button();
        penButton = new Button();
        lineButton = new Button();
        ovalButton = new Button();
        rectangleButton = new Button();
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        widthSlider = new Slider(1,10,1);
        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);
        widthSliderLabel = new Label("1.0");
        //set handlers:
        /*colorPicker.setOnAction(e->{
            colorPicker.getValue());
        });*/

        ovalButton.setOnAction(e->{
            currentShape = new CircleShape();
        });
        lineButton.setOnAction(e -> {
            currentShape = new SingleLine();
        });

        rectangleButton.setOnAction(e->{
            currentShape = new RectangleShape();
        });

        testB = false;
        cursorButton.setOnAction(e->{
            testB = true;
        });

        fillCheckBox.setOnAction(e->{
            if(currentShape!=null)
            {
                currentShape.setFilled(fillCheckBox.isSelected());
            }
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
        rectangleButton.setGraphic(new ImageView("/sample/Resources/polygon.png"));

        //add items to toolbar:
        toolBar.getItems().addAll(cursorButton, penButton,lineButton,ovalButton, rectangleButton, widthSlider, widthSliderLabel,colorPicker, fillCheckBox);
    }

    boolean testB;

    private  void initCanvas()
    {



        centerPane.setOnMousePressed(e->{
            System.out.println("x: " +e.getX() +" y: " +e.getY());
            if(testB)
            {
                //target object:
                List<Shape> list = drawDocument.readDrawData();
                javafx.scene.shape.Shape shape = (javafx.scene.shape.Shape) e.getTarget();

            }else {
                currentShape.setFilled(fillCheckBox.isSelected());
                currentShape.setColor(colorPicker.getValue());
                currentShape.setThickness(widthSlider.getValue());
                System.out.println("current color: " + colorPicker.getValue() + " and shape color ="+currentShape.getColor());
                currentShape.setX(e.getX());
                currentShape.setY(e.getY());
            }


        });

        centerPane.setOnMouseDragged(e->{
        });

        centerPane.setOnMouseReleased(e -> {
            System.out.println("x: " +e.getX() +" y: " +e.getY());
            System.out.println("Mouse released");
            if(!testB)
            {

                currentShape.setEnd(e.getX(), e.getY());
                currentShape.setHeight(e.getY()-currentShape.getY());
                currentShape.setWidth(e.getSceneX()-currentShape.getX());
                currentShape.setRadius(Math.hypot(Math.abs(currentShape.getX()-e.getX()),Math.abs(currentShape.getY()-e.getY())));
                System.out.println("AFTER current color: " + colorPicker.getValue() + " and shape color ="+currentShape.getColor());
                drawDocument.writeDrawData(currentShape);
                drawDocument.notifyAllObservers();
            }
            else
            {
                testB = false;
            }




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
        System.out.println("children antal after update: " + centerPane.getChildren().size());
    }
}
