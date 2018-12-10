package Views;

import Controllers.AController;
import Controllers.CreateVacationsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class CreateVacationsView implements IView {

    @FXML
    public Button b_done;
    public Button b_addPassenger;
    public TextField tf_ticketNum;
    public TextField tf_flightCompany;
    public CheckBox cb_includeFlightBacl;
    public TextField tf_departueFrom;
    public ComboBox cb_passangerType;
    public TextField tf_destination;
    public TextField tf_timeToStay;
    public TextField tf_vacationType;
    public ComboBox cb_hotel;
    public CheckBox cb_includeVacation;
    public CheckBox cb_luggage;
    public TextField tf_weight;
    public TextField tf_height;
    public TextField tf_width;
    public DatePicker dp_flightDate;


    public TextField getTf_ticketNum() {
        return tf_ticketNum;
    }

    public TextField getTf_flightCompany() {
        return tf_flightCompany;
    }

    public CheckBox getCb_includeFlightBacl() {
        return cb_includeFlightBacl;
    }

    public TextField getTf_departueFrom() {
        return tf_departueFrom;
    }

    public ComboBox getCb_passangerType() {
        return cb_passangerType;
    }

    public TextField getTf_destination() {
        return tf_destination;
    }

    public TextField getTf_timeToStay() {
        return tf_timeToStay;
    }

    public TextField getTf_vacationType() {
        return tf_vacationType;
    }

    public ComboBox getCb_hotel() {
        return cb_hotel;
    }

    public CheckBox getCb_includeVacation() {
        return cb_includeVacation;
    }

    public CheckBox getCb_luggage() {
        return cb_luggage;
    }

    public TextField getTf_weight() {
        return tf_weight;
    }

    public TextField getTf_height() {
        return tf_height;
    }

    public TextField getTf_width() {
        return tf_width;
    }

    public DatePicker getDp_flightDate() {
        return dp_flightDate;
    }

    private CreateVacationsController controller;

    public CreateVacationsView() {

    }

    @Override
    public void setController(AController controller) {
        this.controller = (CreateVacationsController) controller;
    }

    public void addPassenger(ActionEvent actionEvent) {

            controller.notifyPassengerAdded();

        }

    public void saveTicket(ActionEvent actionEvent) {
        controller.saveTickets();

    }
}
