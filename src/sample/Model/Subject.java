package sample.Model;


import sample.View.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 2017-03-08.
 */
public class Subject {
    List<Observer> observerList;

    public Subject() {
        observerList = new ArrayList<>();
    }

    public void notifyAllObservers()
    {
        for(Observer o: observerList) {
            o.update();
        }
    }

    public void attach(Observer observer){
        observerList.add(observer);
    }

    public void detach(Observer observer){
        observerList.remove(observer);
    }

}