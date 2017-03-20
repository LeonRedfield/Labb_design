package sample.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.Model.*;
import sample.Model.Shape;

import java.util.ArrayList;

/**
 * Created by Teddy on 2017-03-08.
 */
public class DrawView extends CanvasView implements Observer{
    private Scene scene;
    private VBox topContainer;
    private BorderPane rootPane;
    private VBox centerPane;
    private ToolBar toolBar;
    private Slider widthSlider;
    private Label widthSliderLabel;
    private ColorPicker colorPicker;
    private Button penButton, lineButton, ovalButton, rectangleButton, cursorButton;
    private CheckBox fillCheckBox;
    private Button editButton;
    private DrawDocument drawDocument;
    private UndoMenu undoMenu;
    private RedoMenu redoMenu;
    //shapes:
    private Shape currentShape;
    private CirclePrototype circlePrototype;
    private RectanglePrototype rectanglePrototype;
    private LinePrototype linePrototype;

    public DrawView(DrawDocument drawDocument)
    {
        super();
        this.drawDocument = drawDocument;
        undoMenu = new UndoMenu();
        redoMenu = new RedoMenu();
        circlePrototype = new CircleShape();
        rectanglePrototype = new RectangleShape();
        linePrototype = new SingleLine();
        this.centerPane = new VBox();

        drawDocument.attach(this);
        rootPane = new BorderPane();
        topContainer = new VBox();
        toolBar = new ToolBar();
        topContainer.getChildren().add(super.getAbstractMenubar());
        topContainer.getChildren().add(toolBar);
        rootPane.setTop(topContainer);
        rootPane.setCenter(centerPane);
        //set Scene:
        scene = new Scene(rootPane, super.windowWidth, super.windowHeight);
        rootPane.setStyle("-fx-background-color: white");
        initiateMenu();

        //Init undo/redo menu item handlers
        //(Undo)
        editMenuItems.get(0).setOnAction(e -> {
            if(!undoMenu.isEmpty()){
                EditCommand c = undoMenu.pop();
                System.out.println("undo command: " +c.toString());
                c.undo();
                redoMenu.push(c);
                drawDocument.notifyAllObservers();
            }

        });
        //(redo)
        editMenuItems.get(1).setOnAction(e -> {
            if(!redoMenu.isEmpty()) {
                EditCommand c = redoMenu.pop();
                c.redo();
                undoMenu.push(c);
                drawDocument.notifyAllObservers();
            }
        });

        initCanvas();
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
        fillCheckBox.setDisable(true);
        cursorButton = new Button();
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        widthSlider = new Slider(1,10,1);
        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);
        widthSliderLabel = new Label("1.0");
        editButton = new Button("Edit");

        editButton.setOnAction(e->{
            if(currentShape!=null)
            {
                //currentShape.setColor(colorPicker.getValue());
                //currentShape.setThickness(widthSlider.getValue());
                //currentShape.setFilled(fillCheckBox.isSelected());
                drawDocument.editDrawData(OGShape, currentShape);
            }
        });
        //set handlers:
        colorPicker.setOnAction(e->{
            if(currentShape!=null)
            {
                currentShape.setColor(colorPicker.getValue());
            }
        });

        linePrototype.getLineButton().setOnAction(e -> {
            currentShape = new SingleLine();
            editmode = false;
            editButton.setDisable(true);
            fillCheckBox.setDisable(true);
        });

        rectanglePrototype.getRectangleButton().setOnAction(e->{
            fillCheckBox.setDisable(false);
            currentShape = new RectangleShape();
            editmode = false;
            editButton.setDisable(true);
        });

        circlePrototype.getOvalButton().setOnAction(event -> {
            fillCheckBox.setDisable(false);
            currentShape = new CircleShape();
            editmode = false;
            editButton.setDisable(true);
        });

        editmode = false;
        cursorButton.setOnAction(e->{
            editmode = true;
            editButton.setDisable(false);
        });


        widthSlider.valueProperty().addListener(e->{
            double value = widthSlider.getValue();
            String valueStr = String.format("%.1f", value);
            widthSliderLabel.setText(valueStr);
            if(currentShape!=null)
            {
                currentShape.setStrokeWidth(value);
            }
        });

        //set icons to buttons:
        cursorButton.setGraphic(new ImageView("/sample/Resources/cursor.png"));
        linePrototype.getLineButton().setGraphic(new ImageView("/sample/Resources/line.png"));
        circlePrototype.getOvalButton().setGraphic(new ImageView("/sample/Resources/oval.png"));
        rectanglePrototype.getRectangleButton().setGraphic(new ImageView("/sample/Resources/polygon.png"));

        //add items to toolbar:
        toolBar.getItems().addAll(cursorButton, circlePrototype.getOvalButton(), rectanglePrototype.getRectangleButton(), linePrototype.getLineButton(), fillCheckBox);
        toolBar.getItems().addAll(widthSlider, widthSliderLabel,colorPicker, editButton);
    }

    private boolean editmode;
    private Shape OGShape;

    private  void initCanvas()
    {
        centerPane.setOnMousePressed(e->{
            //System.out.println("x: " +e.getX() +" y: " +e.getY());

            if(editmode)
            {
                javafx.scene.shape.Shape tmpShape = (javafx.scene.shape.Shape) e.getTarget();

                currentShape = OGShape = drawDocument.getShapeOfFigureShape(tmpShape);

            }else {
                currentShape = configShape(currentShape, e);
                currentShape.setColor(colorPicker.getValue());
                currentShape.setStrokeWidth(widthSlider.getValue());
            }

        });

        centerPane.setOnMouseDragged(e->{
        });

        centerPane.setOnMouseReleased(e -> {
            //System.out.println("x: " +e.getX() +" y: " +e.getY());
            //System.out.println("Mouse released");
            if(!editmode)
            {
                currentShape = configShape(currentShape, e);
                EditCommand c = drawDocument.writeDrawData(currentShape);
                undoMenu.push(c);

                drawDocument.notifyAllObservers();
            }
            else
            {
                editmode = false;
            }

        });
    }



    private Shape configShape(Shape shape, MouseEvent e)
    {
        if(shape instanceof SingleLine)
        {
            System.out.println("******************eventType=" + e.getEventType());
            if(e.getEventType() == e.MOUSE_PRESSED)
            {
                ((SingleLine) shape).setStartX(e.getX());
                ((SingleLine) shape).setStartY(e.getY());
            }
            else if(e.getEventType() == e.MOUSE_RELEASED)
            {
                ((SingleLine) shape).setEndX(e.getX());
                ((SingleLine) shape).setEndY(e.getY());
            }

        }
        else if(shape instanceof RectangleShape)
        {
            RectangleShape r = ((RectangleShape) shape);
            if(e.getEventType() == e.MOUSE_PRESSED)
            {
                //r.setFilled(fillCheckBox.isSelected());
                r.setStartX(e.getX());
                r.setStartY(e.getY());
            }
            else if(e.getEventType() == e.MOUSE_RELEASED)
            {
                r.setFilled(fillCheckBox.isSelected());
                r.setHeight(e.getY()-r.getStartY());
                r.setWidth(e.getSceneX()-r.getStartX());
            }
            shape = r;
        }
        else if(shape instanceof CircleShape)
        {
            CircleShape c = ((CircleShape) shape);
            if(e.getEventType() == e.MOUSE_PRESSED)
            {

                c.setCenterX(e.getX());
                c.setCenterY(e.getY());
            }
            else if(e.getEventType() == e.MOUSE_RELEASED)
            {
                c.setFilled(fillCheckBox.isSelected());
                c.setRadius(Math.hypot(Math.abs(c.getCenterX()-e.getX()),Math.abs(c.getCenterY()-e.getY())));
            }
            shape = c;
        }
        return shape;
    }



    @Override
    public void update() {
        //System.out.println("update called by subject");
        Group group = new Group();
        for(Shape s: drawDocument.readDrawData()) {
            group.getChildren().add(s.draw());
        }
        centerPane.getChildren().remove(0,centerPane.getChildren().size());
        //System.out.println("children antal: " + centerPane.getChildren().size());
        centerPane.getChildren().add(group);
        //System.out.println("children antal after update: " + centerPane.getChildren().size());
    }
}
