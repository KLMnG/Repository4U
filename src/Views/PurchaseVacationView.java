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

    public Label lb_response;
    public Label lb_price;
    public TextField tf_creditNumber;
    public ChoiceBox cb_year;
    public ChoiceBox cb_month;
    public Button btn_pay;

    @Override
    public void setController(AController controller) {
        this.controller = (PurchaseVacationController)controller;
        this.controller.initialize();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Purchase success");
        alert.setHeaderText(null);
        alert.setContentText("Thank you for your purchase");
        alert.showAndWait();
    }

    public void pay(ActionEvent actionEvent){
        controller.payment();
    }

    public void back(ActionEvent actionEvent){
        controller.back();
    }

}
