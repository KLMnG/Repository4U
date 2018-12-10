package Views;

import Controllers.AController;
import Controllers.PurchaseVacationController;
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
    public DatePicker d_dateExpiry;
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
}
