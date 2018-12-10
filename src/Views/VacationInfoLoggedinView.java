package Views;

import Controllers.AController;
import Controllers.VacationInfoLoggedinController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class VacationInfoLoggedinView implements IView{


    private VacationInfoLoggedinController controller;

    public Label lb_airline;
    public Label lb_from;
    public Label lb_depart;
    public Label lb_arrival;
    public Label lb_weight;
    public Label lb_to;
    public Label lb_width;
    public Label lb_height;
    public Label lb_timetostay;
    public Label lb_hotelname;
    public Label lb_vacationtype;

    public void setLb_timetostay(Label lb_timetostay) {
        this.lb_timetostay = lb_timetostay;
    }

    public void setLb_hotelname(Label lb_hotelname) {
        this.lb_hotelname = lb_hotelname;
    }

    public void setLb_vacationtype(Label lb_vacationtype) {
        this.lb_vacationtype = lb_vacationtype;
    }

    @Override
    public void setController(AController controller) {
        this.controller = (VacationInfoLoggedinController) controller;
        this.controller.initializeView();
    }

    public void setLb_airline(Label lb_airline) {
        this.lb_airline = lb_airline;
    }

    public void setLb_from(Label lb_from) {
        this.lb_from = lb_from;
    }

    public void setLb_depart(Label lb_depart) {
        this.lb_depart = lb_depart;
    }

    public void setLb_arrival(Label lb_arrival) {
        this.lb_arrival = lb_arrival;
    }

    public void setLb_weight(Label lb_weight) {
        this.lb_weight = lb_weight;
    }

    public void setLb_to(Label lb_to) {
        this.lb_to = lb_to;
    }

    public void setLb_width(Label lb_width) {
        this.lb_width = lb_width;
    }

    public void setLb_height(Label lb_hieght) {
        this.lb_height = lb_hieght;
    }

    public void PurchaseVacation(ActionEvent actionEvent) {
        this.controller.PurchaseVacation();
    }

    public void back(ActionEvent actionEvent) {
        this.controller.back();
    }
}
