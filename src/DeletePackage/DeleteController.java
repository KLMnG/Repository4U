package DeletePackage;



import General.AController;
import General.ModelUserDB;
import interfaces.IPapaListener;

import java.util.ArrayList;

public class DeleteController extends AController {
    private ModelUserDB model;
    private DeleteView view;

    private static ArrayList<IPapaListener> lst;

    public DeleteController(){};

    public DeleteController(ModelUserDB m_model, DeleteView m_view) {
        this.model = m_model;
        this.view = m_view;

 //       this.view.setButtonCreateClickedHandler(new DeleteController.ButtonCreateClickedHandler());

        lst = new ArrayList<IPapaListener>();
    }

    public void deleteUser(){

        try {
            model.delete(view.userTxt.getText());//need to add password
        }
        catch (Exception e){
            view.showError();
        }

    }



//    public void addPapaListener(IPapaListener papa){
//        if (papa != null && !lst.contains(papa))
//            lst.add(papa);
//    }
//
//    public DeleteController.ButtonCreateClickedHandler getButtonCreateClickedHandlerHandler(){
//        return new DeleteController.ButtonCreateClickedHandler();
//    }
//
    @Override
    public void back() {

    }

//    private class ButtonCreateClickedHandler implements EventHandler {
//
//        @Override
//        public void handle(Event event) {
//
//
//
//        }
//
//    }


}
