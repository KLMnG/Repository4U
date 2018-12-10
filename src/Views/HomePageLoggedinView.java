package Views;

import Controllers.AController;
import Controllers.HomePageLoggedInController;
import Models.HomePageLoggedinModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class HomePageLoggedinView implements IView{

    private HomePageLoggedInController controller;

    public Label lb_Gesture;


    public void setUserGesture(String str){
        this.lb_Gesture.setText(str);
    }

    @Override
    public void setController(AController controller) {

        this.controller = (HomePageLoggedInController) controller;
        this.controller.initializeView();
    }

    public void Signout(ActionEvent actionEvent){
        this.controller.Signout();
    }
}
