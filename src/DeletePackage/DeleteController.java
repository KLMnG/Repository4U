package DeletePackage;



import MainPackage.MainModel;
import interfaces.IPapaListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.Optional;

public class DeleteController {
    private MainModel model;
    private DeleteView view;

    private static ArrayList<IPapaListener> lst;

    public DeleteController(MainModel m_model, DeleteView m_view) {
        this.model = m_model;
        this.view = m_view;

        this.view.setButtonCreateClickedHandler(new DeleteController.ButtonCreateClickedHandler());

        lst = new ArrayList<IPapaListener>();
    }

    public void addPapaListener(IPapaListener papa){
        if (papa != null && !lst.contains(papa))
            lst.add(papa);
    }

    public DeleteController.ButtonCreateClickedHandler getButtonCreateClickedHandlerHandler(){
        return new DeleteController.ButtonCreateClickedHandler();
    }

    private class ButtonCreateClickedHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            //model.read(username AND password)
            //if false-> show view.show l_error
            //else (below)
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("confirmation");
            alert.setHeaderText("Delete User");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK)
                System.out.println("model.delete(username)");


        }

    }
}
