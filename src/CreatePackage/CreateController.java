package CreatePackage;

import General.ModelUserDB;
import interfaces.IPapaListener;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;


public class CreateController {

    public ModelUserDB model;
    private CreateView view;


    private static ArrayList<IPapaListener> lst;



    public CreateController(ModelUserDB model, CreateView view) {
        this.model = model;
        this.view = view;
        lst = new ArrayList<IPapaListener>();
        //    this.view.setButtonCreateClickedHandler(new ButtonCreateClickedHandler());

    }

    //   public void addPapaListener(IPapaListener papa){
    //     if (papa != null && !lst.contains(papa))
    //       lst.add(papa);
    // }

    public void b_create(){
        model.addUser(view.getUserName(),view.getPassword(),view.getFirstName(),view.getLastName(),view.getBirthDate(),view.getCity());
    }

    private class ButtonCreateClickedHandler implements EventHandler{

        @Override
        public void handle(Event event) {
            model.addUser(view.getUserName(),view.getPassword(),view.getFirstName(),view.getLastName(),view.getBirthDate(),view.getCity());
        }
    }
}

