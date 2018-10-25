package MainPackage;

import javafx.event.Event;
import javafx.event.EventHandler;
import interfaces.IPapaListener;
import java.util.ArrayList;

public class MainController {

    private MainModel model;
    private MainView view;

    private static ArrayList<IPapaListener> lst;

    public MainController(MainModel m_model, MainView m_view) {
        this.model = m_model;
        this.view = m_view;

        this.view.setButtonCreateClickedHandler(new ButtonCreateClickedHandler());

        lst = new ArrayList<IPapaListener>();
    }

    public void addPapaListener(IPapaListener papa){
        if (papa != null && !lst.contains(papa))
            lst.add(papa);
    }

    public ButtonCreateClickedHandler getButtonCreateClickedHandlerHandler(){
        return new ButtonCreateClickedHandler();
    }

    private class ButtonCreateClickedHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            for (IPapaListener papa : lst) {
                papa.SwapScene(view.getScene());
            }
        }
    }

}
