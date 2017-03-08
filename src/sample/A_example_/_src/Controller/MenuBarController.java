package sample.A_example_._src.Controller;

import Model.ConfirmationBox;
import View.GamePlayView;
import View.MainMenuView;
import View.Views;
import View.ViewsList;
import World.World;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Teddy
 */
public class MenuBarController {
    private Views currentview;
    private MenuBarHandler menuhandler;
    private MenuItem[] items;
    private static boolean gameplaystop;
    private static GamePlayView gameplayview;

    
    public MenuBarController(Views view)
    {
        this.currentview = view;
        menuhandler = new MenuBarHandler();
        items = currentview.getMenuItems();

    }
    

    public MenuBarHandler getHandler()
    {
        return menuhandler;
    }
    

    private class MenuBarHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            
           if(event.getSource() == items[0])
            {
                if(ViewsList.getCurrentViewIndex() == 0)
                {              
                    ViewsList.changeView(1);
                    World.getWindow().setScene(ViewsList.getCurrentViewScene()); 
                }
            }
            else if(event.getSource() == items[1])
            {
                if(ViewsList.getCurrentViewIndex() == 2)
                {              
                    ViewsList.changeView(3);
                    World.getWindow().setScene(ViewsList.getCurrentViewScene()); 
                }
            }
            else if(event.getSource() == items[2])
            {
                if(ViewsList.getCurrentViewIndex() == 3)
                {              
                    
                    ViewsList.changeView(2);
                    World.getWindow().setScene(ViewsList.getCurrentViewScene());
                }
            }
            else if(event.getSource() == items[3])
            {
                if(currentview == ViewsList.getCurrentViewByIndex(2))
                {              
                    gameplayview = (GamePlayView) currentview;
                    gameplayview.stopGamePlayTimer();     
                    System.out.println("innne i gamplay och tiden är stoppad");
                    gameplaystop = true;
                }
               else
               {
                   gameplaystop = false;
               }
                ConfirmationBox.showPictureBox("Instructions", "Ready to play now?", "tetriscontrols.png");
                
                if(gameplaystop)
                {
                     gameplayview.startGamePlayTimer();
                }
                              
            }
           
        }
        
    }
     
}

