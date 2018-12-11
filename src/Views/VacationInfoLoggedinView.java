package Views;

import Controllers.AController;
import Controllers.VacationInfoLoggedinController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class VacationInfoLoggedinView implements IView{


    private VacationInfoLoggedinController controller;

    public Label lb_airline;
    public Label lb_from;
    public Label lb_depart;
    public Label lb_return;
    public Label lb_weight;
    public Label lb_to;
    public Label lb_width;
    public Label lb_height;
    public Label lb_timetostay;
    public Label lb_hotelname;
    public Label lb_vacationtype;

    @Override
    public void setController(AController controller) {
        this.controller = (VacationInfoLoggedinController) controller;
        this.controller.initializeView();
    }

    public void setLb_hotelname(String lb_hotelname) {
        this.lb_hotelname.setText(lb_hotelname);
    }

    public void setLb_vacationtype(String lb_vacationtype) {
        this.lb_vacationtype.setText(lb_vacationtype);
    }

    public void setLb_airline(String lb_airline) {
        this.lb_airline.setText(lb_airline);
    }

    public void setLb_from(String lb_from) {
        this.lb_from.setText(lb_from);
    }

    public void setLb_depart(String lb_depart) {
        this.lb_depart.setText(lb_depart);
    }

    public void setLb_return(String lb_arrival) {
        this.lb_return.setText(lb_arrival);
    }

    public void setLb_weight(String lb_weight) {
        this.lb_weight.setText(lb_weight);
    }

    public void setLb_to(String lb_to) {
        this.lb_to.setText(lb_to);
    }

    public void setLb_width(String lb_width) {
        this.lb_width.setText(lb_width);
    }

    public void setLb_height(String lb_height) {
        this.lb_height.setText(lb_height);
    }

    public void back(ActionEvent actionEvent) {
        this.controller.back();
    }

    public void PurchaseVacation(ActionEvent actionEvent) {
        this.controller.PurchaseVacation();
    }

    public void ShowInfoAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,alertMessage, ButtonType.OK);
        alert.show();
    }
    public void ShowErrorAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR,alertMessage, ButtonType.OK);
        alert.show();
    }


}
