package Views;

import Controllers.AController;
import Controllers.PurchaseVacationController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

public class PurchaseVacationView implements IView {

    private PurchaseVacationController controller;

    public Label lb_airline;
    public Label lb_from;
    public Label lb_depart;
    public Label lb_arrival;
    public Label lb_weight;
    public Label lb_to;
    public Label lb_width;
    public Label lb_hieght;
    public Label lb_response;
    public Label lb_price;
    public Label lb_vacationtype;
    public TextField tf_creditNumber;
    public ChoiceBox cb_year;
    public ChoiceBox cb_month;
    public Button btn_pay;

    @Override
    public void setController(AController controller) {
        {
            this.controller = (PurchaseVacationController)controller;
        }
    }


    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm vacation");
        alert.setContentText("Are you sure you want to sell?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... accept
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public PurchaseVacationView(){
        cb_year.getItems().add("2019");
        cb_year.getItems().add("2020");
        cb_year.getItems().add("2021");
        cb_year.getItems().add("2022");
        cb_year.getItems().add("2023");
        cb_year.getItems().add("2024");
        cb_month.getItems().add("01");
        cb_month.getItems().add("02");
        cb_month.getItems().add("03");
        cb_month.getItems().add("04");
        cb_month.getItems().add("05");
        cb_month.getItems().add("06");
        cb_month.getItems().add("07");
        cb_month.getItems().add("08");
        cb_month.getItems().add("09");
        cb_month.getItems().add("10");
        cb_month.getItems().add("11");
        cb_month.getItems().add("12");
    }

    public void pay(ActionEvent actionEvent){
        controller.payment();
    }

    public void back(ActionEvent actionEvent){
        controller.back();
    }
}
