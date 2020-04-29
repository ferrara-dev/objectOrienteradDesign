package view;

import controller.Controller;
import controller.MainController;
import observer.EventObserver;
import util.exception.NotFoundException;
import javax.swing.*;
import java.util.ArrayList;

public abstract class View extends JPanel implements EventObserver {
   protected ArrayList<Controller> registeredController;
   public abstract void update(String s);

   public void addController(Controller controller){
      if(registeredController == null)
         registeredController = new ArrayList<>();
      registeredController.add(controller);
   }

   protected Controller getSaleController(){
         for(Controller controller: registeredController){
            if(controller instanceof MainController)
               return controller;
         }

         throw new NotFoundException();
   }
}
