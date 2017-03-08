/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.View;


import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 *
 * @author Teddy
 */
abstract public class Views {
    protected Scene scene;
    protected Stage rootwindow;
    protected double windowWidth;
    protected double windowHeight;
    protected final  Menu menuFile, menuInstructions;
    protected final  MenuItem itemStart, itemPause, itemResume, itemHowToPlay; 
    protected final  MenuBar menubar;
    public Views()
    {
        //initiate menu:
        menuFile = new Menu("File");
        itemStart = new MenuItem("Start");
        itemPause = new MenuItem("Pause");
        itemResume = new MenuItem("Resume");              
        menuFile.getItems().addAll(itemStart, itemPause, itemResume);
        menuInstructions = new Menu("Instructions");
        itemHowToPlay = new MenuItem("How To Play"); 
        menuInstructions.getItems().addAll(itemHowToPlay);
        menubar = new MenuBar();
        menubar.getMenus().addAll(menuFile, menuInstructions);
        
        setComputerScreenSize();
    }
    
    public Scene getScene()
    {
        return scene;
    }
    
    public MenuItem[] getMenuItems()
    {
        MenuItem[] menuitems = {itemStart,itemPause, itemResume,itemHowToPlay};
        return menuitems;
    }
    
    public Menu[] getMenus()
    {
        Menu[] menus = {menuFile, menuInstructions};
        return menus;
    }
    
    private void setRootWindow(Stage rootwindow)
    {
        this.rootwindow = rootwindow;
    }
    
    private void setComputerScreenSize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // takes computers size and 
        this.windowWidth = screenSize.getWidth()/1.5;
        this.windowHeight = screenSize.getHeight()/1.1;
    }
    
    public void setMenuBarHandler(Views currentview)
    {
        /*MenuBarController control = new MenuBarController(currentview);
        itemStart.setOnAction(control.getHandler());
        itemPause.setOnAction(control.getHandler());
        itemResume.setOnAction(control.getHandler());
        itemHowToPlay.setOnAction(control.getHandler());*/
    }
    
    public double getWidth()
    {
        return windowWidth;
    }
    
    public double getHeight()
    {
        return windowHeight;
    }
    
    
}
