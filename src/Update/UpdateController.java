package Update;

import General.ModelUserDB;
import interfaces.IPapaListener;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class UpdateController {

    private ModelUserDB model;
    private UpdateView view;

    private static ArrayList<IPapaListener> lst;

    public UpdateController(ModelUserDB model, UpdateView view) {
        this.model = model;
        this.view = view;
        lst = new ArrayList<IPapaListener>();

    }

    public void addPapaListener(IPapaListener papa){
        if (papa != null && !lst.contains(papa))
            lst.add(papa);
    }

    private class ButtonLoginClickedHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            String Username = view.getTf_loginId();
            String Passwrod = view.getPf_loginPass();

            ArrayList<String> result = (ArrayList<String>)model.read(Username,Passwrod);

            if (result.size() > 1){
                view.setTf_newId(result.get(0));
                view.setPf_newPass(result.get(1));
                view.setTf_newFname(result.get(2));
                view.setTf_newLname(result.get(3));
                view.setTf_newAddress(result.get(4));
                view.setDp_newBirthdate(result.get(5));
                view.openUpdateLayout();
            }
        }
    }

    private class ButtonUpdateClickedHandler implements EventHandler {

        @Override
        public void handle(Event event) {

        }
    }

}
