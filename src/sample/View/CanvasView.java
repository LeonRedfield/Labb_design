package sample.View;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Teddy on 2017-03-07.
 */
abstract public class CanvasView{
    protected double windowWidth;
    protected double windowHeight;
    protected Menu fileMenu, editMenu, viewMenu;
    protected ArrayList<Menu> menus;
    protected ArrayList<MenuItem> fileMenuItems, editMenuItems, viewMenuItems;
    protected MenuBar abstractMenubar;

    public CanvasView()
    {
        menus = new ArrayList<>();
        fileMenuItems = new ArrayList<>();
        editMenuItems = new ArrayList<>();
        viewMenuItems = new ArrayList<>();
        setComputerScreenSize();
        initMenu();
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

    public MenuBar getAbstractMenubar() {
        return abstractMenubar;
    }

    private void setComputerScreenSize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // takes computers size and
        this.windowWidth = screenSize.getWidth()/1.5;
        this.windowHeight = screenSize.getHeight()/1.1;
    }

    private void initMenu()
    {
        //initiate menu:
        abstractMenubar = new MenuBar();
        //Menu:
        fileMenu = new Menu("File");
        editMenu = new Menu("Edit");
        viewMenu = new Menu("View");
        //Menu items for fileMenu:
        fileMenuItems.add(new MenuItem("New"));
        fileMenuItems.add(new MenuItem("Save"));
        fileMenuItems.add(new MenuItem("Save As"));
        fileMenuItems.add(new MenuItem("Open"));
        fileMenuItems.add(new MenuItem("Close"));
        //bind items to key combinations:
        fileMenuItems.get(0).setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        fileMenuItems.get(1).setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        fileMenuItems.get(2).setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        fileMenuItems.get(3).setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        // set handlers:

        //add items to
        fileMenu.getItems().addAll(fileMenuItems);
        // add menus to list
        menus.add(fileMenu);
        menus.add(editMenu);
        menus.add(viewMenu);

        abstractMenubar.getMenus().addAll(menus);
    }
}
