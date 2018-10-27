package ReadPackage;

import General.ModelUserDB;
import interfaces.IPapaListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.List;

public class ReadController {

    private ReadView view;
    private ModelUserDB model;

    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userAddress;
    private String userBirthDate;

    private static ArrayList<IPapaListener> lst;

    public ReadController(ModelUserDB r_model, ReadView r_view) {
        this.view = r_view;
        this.model = r_model;

        this.view.setButtonSearchClickedHandler(new ButtonSearchClickedHandler());
    }

    public ButtonSearchClickedHandler getButtonSearchClickedHandler(){
        return new ButtonSearchClickedHandler();
    }


    public void addPapaListener(IPapaListener papa){
        if (papa != null && !lst.contains(papa))
            lst.add(papa);
    }

    private class ButtonSearchClickedHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            List userInfo = model.read(view.getUserNameFromTextField());
            if(userInfo != null && userInfo.size() != 0) {
                separationInfo(userInfo);
                view.setVisibleTrue();
                view.setUserName(userName);
                view.setUserPassword(userPassword);
                view.setUserFirstName(userFirstName);
                view.setUserLastName(userLastName);
                view.setUserAddress(userAddress);
                view.setUserBirthDate(userBirthDate);
            }
            else{
                view.setVisibleFalse();
            }

        }
    }

    private void separationInfo(List userInfo) {
        userName = (String) userInfo.get(0);
        userPassword = (String) userInfo.get(1);
        userFirstName = (String) userInfo.get(2);
        userLastName = (String) userInfo.get(3);
        userAddress = (String) userInfo.get(4);
        userBirthDate = (String) userInfo.get(5);
    }
}
