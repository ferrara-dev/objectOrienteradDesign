package view.storesystemview;

import observer.EventObserver;
import observer.ObservedEvent;
import view.MainView;
import view.View;

import javax.swing.*;
import java.util.ArrayList;

public class SystemView extends MainView {
    private RevenueMainView revenueMainView;
    private JTabbedPane tabbedPane1;

    public SystemView(){
        revenueMainView = new RevenueMainView();
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.add(revenueMainView, "Bank services");
        add(tabbedPane1);
    }

    public ArrayList<EventObserver> collectEventObservers(){
        ArrayList<EventObserver> eventObservers = new ArrayList<>();
        eventObservers.add(revenueMainView);
        eventObservers.add(this);
        return eventObservers;
    }

    @Override
    public ArrayList<View> collectViews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(revenueMainView);
        return views;
    }

    @Override
    public void update(String s) {
        revenueMainView.update(s);
    }

    @Override
    public void newEvent(ObservedEvent observedEvent) {

    }

    @Override
    public long getId() {
        return 0;
    }
}
