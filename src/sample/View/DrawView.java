package sample.View;

import javafx.scene.Scene;
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
    private Button penButton, lineButton, ovalButton, polygonButton;
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
        initiateMenu();
        //set Scene:
        scene = new Scene(rootPane, super.windowWidth, super.windowHeight);
        scene.setFill(Color.WHITE);

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
        penButton = new Button();
        lineButton = new Button();
        ovalButton = new Button();
        polygonButton = new Button();
        // width slider
        widthSlider = new Slider(1,5,1);

        //set icons to buttons:
        penButton.setGraphic(new ImageView("/sample/Resources/pen.png"));
        lineButton.setGraphic(new ImageView("/sample/Resources/line.png"));
        ovalButton.setGraphic(new ImageView("/sample/Resources/oval.png"));
        polygonButton.setGraphic(new ImageView("/sample/Resources/polygon.png"));

        //add items to toolbar:
        toolBar.getItems().addAll(penButton,lineButton,ovalButton, polygonButton, widthSlider);

    }
}
