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

    public Button b_got;

    @Override
    public void setController(AController controller) {
        this.controller = (PurchaseVacationController)controller;
    }

    public void got(ActionEvent actionEvent){
        controller.gotMoney();
    }

    public void back(ActionEvent actionEvent){
        controller.back();
    }

    public void showWarning(String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thank you !");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }



}
