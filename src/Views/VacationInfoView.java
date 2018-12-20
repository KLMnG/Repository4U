package Views;

import Controllers.AController;
import Controllers.VacationInfoController;
import General.TicketData;
import General.VacationData;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class VacationInfoView implements IView{


    private VacationInfoController controller;

    public Label lb_weight;
    public Label lb_width;
    public Label lb_height;
    public Label lb_timetostay;
    public Label lb_hotelname;
    public Label lb_vacationtype;
    public Label lb_address;
    public Label lb_rate;

    public TableView tbl_tickets;
    public TableColumn col_flightCompany;
    public TableColumn col_departure;
    public TableColumn col_destination;
    public TableColumn col_flghtDate;
    public TableColumn col_flghtBack;
    public AnchorPane ap_luggage;
    public AnchorPane ap_hotel;
    public AnchorPane ap_moreinfo;

    private ObservableList<TicketData> data;
    private SimpleBooleanProperty luggageBinding;
    private SimpleBooleanProperty hotelBinding;
    private SimpleBooleanProperty moreinfoBinding;


    public void initialize(){

        this.data = FXCollections.observableArrayList();
        this.luggageBinding = new SimpleBooleanProperty(false);
        this.hotelBinding = new SimpleBooleanProperty(false);
        this.moreinfoBinding = new SimpleBooleanProperty(false);

        this.ap_luggage.visibleProperty().bind(luggageBinding);
        this.ap_hotel.visibleProperty().bind(hotelBinding);
        this.ap_moreinfo.visibleProperty().bind(moreinfoBinding);

        this.tbl_tickets.setRowFactory(param -> {TableRow<TicketData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    TicketData rowData = row.getItem();
                    this.luggageBinding.setValue(true);
                    this.SetLuggageData(rowData);
                }
                else if(row.isEmpty())
                    this.luggageBinding.setValue(false);
            });
            return row ;
        });
        col_flightCompany.setCellValueFactory(
                new PropertyValueFactory<TicketData,String>("Airline")
        );
        col_departure.setCellValueFactory(
                new PropertyValueFactory<TicketData,String>("From")
        );
        col_destination.setCellValueFactory(
                new PropertyValueFactory<TicketData,String>("To")
        );
        col_flghtDate.setCellValueFactory(
                new PropertyValueFactory<TicketData,String>("Depart")
        );
        col_flghtBack.setCellValueFactory(
                new PropertyValueFactory<TicketData, Boolean>("includes_flight_back")
        );

        this.tbl_tickets.setItems(data);
    }

    public void setLb_timetostay(String lb_timetostay) {
        this.lb_timetostay.setText(lb_timetostay);
    }

    @Override
    public void setController(AController controller) {
        this.controller = (VacationInfoController) controller;
        this.controller.initializeView();
    }

    public void setLb_hotelname(String lb_hotelname) {
        if (lb_hotelname == null)
            this.hotelBinding.setValue(false);
        else
            this.hotelBinding.setValue(true);
        this.lb_hotelname.setText(lb_hotelname);
    }

    public void setLb_vacationtype(String lb_vacationtype) {
        if (lb_vacationtype == null)
            moreinfoBinding.setValue(false);
        else
            moreinfoBinding.setValue(true);
        this.lb_vacationtype.setText(lb_vacationtype);
    }

    public void setLb_address(String lb_address) {
        this.lb_address.setText(lb_address);
    }

    public void setLb_rate(String lb_rate) {
        this.lb_rate.setText(lb_rate);
    }

    public void back(ActionEvent actionEvent) {
        this.controller.back();
    }

    public void setTicketTableView(List<TicketData> ticketTableView) {
        this.data.clear();
        this.data.setAll(ticketTableView);
    }

    private void SetLuggageData(TicketData rowData) {
        this.lb_height.setText(rowData.getHeight() + "");
        this.lb_weight.setText(rowData.getWeight() + "");
        this.lb_width.setText(rowData.getWidth() + "");
    }

}
