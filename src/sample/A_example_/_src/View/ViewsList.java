/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.View;

import javafx.scene.Scene;

/**
 *
 * @author Teddy
 */
/*
public class ViewsList {
    private static  Views[] viewsList;
    private static int currentViewIndex;
    private static Views currentView;
    private final Model model;

    public ViewsList(Model model)
    {
        //initiate menu:

        this.model = model;
        currentViewIndex = 0;
        viewsList = new Views[]{
                    new MainMenuView(model),
                    new SettingsView(model),
                    new GamePlayView(model),
                    new PauseMenuView(model),
                    new HighScoreView(model)                  
                };
        currentView = viewsList[currentViewIndex];
    }
    
    //test ta bort senar:
    public static int getCurrentViewIndex()
    {
        return currentViewIndex;
    }   
    
    public static Scene getCurrentViewScene()
    {
        return viewsList[currentViewIndex].getScene();
    }
    
    
    public static void changeView(int index)
    {
        currentViewIndex = index;        
    }
    
    public static Views getCurrentView()
    {
        return currentView = viewsList[currentViewIndex];
    }
    
    public static Views getCurrentViewByIndex(int index)
    {
        return viewsList[index];
    }
    */
    
    

    /*public static void resetView(int index)
    {
        Model newmodel = new Model();
        switch(index)
        {
            case 0: viewsList[0] = new MainMenuView(newmodel);
                break;
            case 1:
                viewsList[1] = new SettingsView(newmodel);
                break;
            case 2:
                viewsList[2] = new GamePlayView(newmodel);
                break;
            case 3:
                viewsList[3] = new PauseMenuView(newmodel);
                break;
            case 4:
                viewsList[4] = new HighScoreView(newmodel);
                break;
            default:     
        }
    }*/
    
    /*public static void resetViews()
    {

        Model newmodel = new Model();
        viewsList = new Views[]{
                    new MainMenuView(newmodel),
                    new SettingsView(newmodel),
                    new GamePlayView(newmodel),
                    new PauseMenuView(newmodel),
                    new HighScoreView(newmodel)                  
                };
    }
}*/
