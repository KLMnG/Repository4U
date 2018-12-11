package Views;

import Controllers.AController;
import Controllers.HomePageLoggedInController;
import Models.HomePageLoggedinModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomePageLoggedinView implements IView{

    private HomePageLoggedInController controller;

    public TableView tv_vacations;
    public TableColumn col_from;
    public TableColumn col_to;
    public TableColumn col_days;
    public TableColumn col_depart;
    public TableColumn col_travelers;
    public TableColumn col_price;

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
