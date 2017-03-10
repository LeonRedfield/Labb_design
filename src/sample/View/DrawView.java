package sample.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
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
    private Shape currentShape;
    private Button editButton;
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
                EditCommand c = undoMenu.pop();
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
        editButton = new Button("Edit");

        editButton.setOnAction(e->{
            if(currentShape!=null)
            {
                //currentShape.setColor(colorPicker.getValue());
                //currentShape.setThickness(widthSlider.getValue());
                currentShape.setFilled(fillCheckBox.isSelected());
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

        ovalButton.setOnAction(e->{
            currentShape = new CircleShape();
            editmode = false;
            editButton.setDisable(true);
        });
        lineButton.setOnAction(e -> {
            currentShape = new SingleLine();
            editmode = false;
            editButton.setDisable(true);
        });

        rectangleButton.setOnAction(e->{
            currentShape = new RectangleShape();
            editmode = false;
            editButton.setDisable(true);
        });

        editmode = false;
        cursorButton.setOnAction(e->{
            editmode = true;
            editButton.setDisable(false);
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
            if(currentShape!=null)
            {
                currentShape.setThickness(value);
            }
        });

        //set icons to buttons:
        cursorButton.setGraphic(new ImageView("/sample/Resources/cursor.png"));
        penButton.setGraphic(new ImageView("/sample/Resources/pen.png"));
        lineButton.setGraphic(new ImageView("/sample/Resources/line.png"));
        ovalButton.setGraphic(new ImageView("/sample/Resources/oval.png"));
        rectangleButton.setGraphic(new ImageView("/sample/Resources/polygon.png"));

        //add items to toolbar:
        toolBar.getItems().addAll(cursorButton, penButton,lineButton,ovalButton, rectangleButton, widthSlider, widthSliderLabel,colorPicker, fillCheckBox, editButton);
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
                currentShape.setFilled(fillCheckBox.isSelected());
                currentShape.setColor(colorPicker.getValue());
                currentShape.setThickness(widthSlider.getValue());

                currentShape.setX(e.getX());
                currentShape.setY(e.getY());
            }


        });

        centerPane.setOnMouseDragged(e->{
        });

        centerPane.setOnMouseReleased(e -> {
            //System.out.println("x: " +e.getX() +" y: " +e.getY());
            //System.out.println("Mouse released");
            if(!editmode)
            {
                currentShape.setEnd(e.getX(), e.getY());
                currentShape.setHeight(e.getY()-currentShape.getY());
                currentShape.setWidth(e.getSceneX()-currentShape.getX());
                currentShape.setRadius(Math.hypot(Math.abs(currentShape.getX()-e.getX()),Math.abs(currentShape.getY()-e.getY())));
                System.out.println("AFTER current color: " + colorPicker.getValue() + " and shape color ="+currentShape.getColor());

                EditCommand c = drawDocument.writeDrawData(currentShape);
                undoMenu.push(c);
                drawDocument.writeDrawData(currentShape);

                drawDocument.notifyAllObservers();
            }
            else
            {
                editmode = false;
            }

        });
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
