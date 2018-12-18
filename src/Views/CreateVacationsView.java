package Views;

import Controllers.AController;
import Controllers.CreateVacationsController;
import Controls.DateTimePicker;
import General.TicketData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public ChoiceBox cb_hotel;
    public CheckBox cb_includeVacation;
    public CheckBox cb_luggage;
    public TextField tf_weight;
    public TextField tf_height;
    public TextField tf_width;
    public DateTimePicker dp_flightDate;
    public Label lb_hotel;
    public Label lb_vacationType;
    public Label lb_timeToStay;
    public Label lb_weight;
    public Label lb_height;
    public Label lb_width;
    public TextField tf_price;
    public TableColumn col_ticketNum;
    public TableColumn col_flightCompany;
    public TableColumn col_departure;
    public TableColumn col_destination;
    public TableColumn col_flghtDate;
    public TableView tbl_tickets;

    private ObservableList<TicketData> data;

    public void initialize() {

        this.cb_passangerType.getItems().addAll(
                "Regular",
                "Children",
                "Baby"
        );
        this.cb_passangerType.setValue("Regular");


        this.data = FXCollections.observableArrayList();

//        tbl_tickets.setRowFactory(param -> {TableRow<TicketData> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
//                    tbl_tickets rowData = row.getItem();
//                    this.controller.openVacationInfoWindows();
//                }
//            });
//            return row ;
//        });

        col_ticketNum.setCellValueFactory(
                new PropertyValueFactory<TicketData, String>("code")
        );
        col_flightCompany.setCellValueFactory(
                new PropertyValueFactory<TicketData, String>("Airline")
        );
        col_departure.setCellValueFactory(
                new PropertyValueFactory<TicketData, String>("From")
        );
        col_destination.setCellValueFactory(
                new PropertyValueFactory<TicketData, String>("To")
        );
        col_flghtDate.setCellValueFactory(
                new PropertyValueFactory<TicketData, String>("Depart")
        );

        this.tbl_tickets.setItems(data);
    }

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

    public ChoiceBox getCb_hotel() {
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

    @Override
    public void setController(AController controller) {
        this.controller = (CreateVacationsController) controller;
        this.controller.initialize();
    }

    public void addPassenger(ActionEvent actionEvent) {
        int weight = -1;
        int height = -1;
        int width = -1;
        if (cb_luggage.isSelected()) {

            try {
                weight = Integer.parseInt(this.tf_weight.getText());
                height = Integer.parseInt(this.tf_weight.getText());
                width = Integer.parseInt(this.tf_weight.getText());
            } catch (NumberFormatException e) {
                weight = 0;
                height = 0;
                width = 0;
            }

        }
        if (this.controller.ValidateInput()) {
            TicketData data = new TicketData(this.tf_ticketNum.getText(), this.tf_departueFrom.getText(), this.tf_destination.getText(), this.tf_departueFrom.getText(), this.tf_flightCompany.getText(), weight,height,width, this.tf_vacationType.getText(), (this.cb_includeFlightBacl.isSelected() ? 1 : 0));
            this.data.add(data);
            controller.notifyPassengerAdded();
        }
    }

    public void saveTicket(ActionEvent actionEvent) {
        controller.saveTickets();
        controller.swapScene();
    }

    public void showVacationTributes(ActionEvent actionEvent) {
        if (cb_includeVacation.isSelected()) {
            tf_timeToStay.visibleProperty().setValue(true);
            tf_vacationType.visibleProperty().setValue(true);
            cb_hotel.visibleProperty().setValue(true);
            lb_hotel.visibleProperty().setValue(true);
            lb_vacationType.visibleProperty().setValue(true);
            lb_timeToStay.visibleProperty().setValue(true);
        } else {
            tf_timeToStay.visibleProperty().setValue(false);
            tf_vacationType.visibleProperty().setValue(false);
            cb_hotel.visibleProperty().setValue(false);
            lb_hotel.visibleProperty().setValue(false);
            lb_vacationType.visibleProperty().setValue(false);
            lb_timeToStay.visibleProperty().setValue(false);

        }
    }


    public void showLuggageAtribuets(ActionEvent actionEvent) {
        if (cb_luggage.isSelected()) {
            lb_weight.visibleProperty().setValue(true);
            lb_height.visibleProperty().setValue(true);
            lb_width.visibleProperty().setValue(true);
            tf_weight.visibleProperty().setValue(true);
            tf_height.visibleProperty().setValue(true);
            tf_width.visibleProperty().setValue(true);
        } else {
            lb_weight.visibleProperty().setValue(false);
            lb_height.visibleProperty().setValue(false);
            lb_width.visibleProperty().setValue(false);
            tf_weight.visibleProperty().setValue(false);
            tf_height.visibleProperty().setValue(false);
            tf_width.visibleProperty().setValue(false);
        }
    }

    public TextField getTf_price() {
        return tf_price;
    }

    public void back() {
        controller.back();
    }

    public void ShowErrorAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR, alertMessage, ButtonType.OK);
        alert.show();
    }

}
