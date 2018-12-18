package Views;

import Controllers.AController;
import Controllers.VacationInfoController;
import General.TicketData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class VacationInfoView implements IView{



    private VacationInfoController controller;

    public Label lb_airline;
    public Label lb_return;
    public Label lb_weight;
    public Label lb_width;
    public Label lb_height;
    public Label lb_timetostay;
    public Label lb_hotelname;
    public Label lb_vacationtype;

    public TableView tbl_tickets;
    public TableColumn col_flightCompany;
    public TableColumn col_departure;
    public TableColumn col_destination;
    public TableColumn col_flghtDate;

    private ObservableList<TicketData> data;

    public void initialize(){

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

        this.tbl_tickets.setItems(data);
    }

    public void setLb_timetostay(Label lb_timetostay) {
        this.lb_timetostay = lb_timetostay;
    }


    @Override
    public void setController(AController controller) {
        this.controller = (VacationInfoController) controller;
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

    public void setLb_return(String lb_arrival) {
        this.lb_return.setText(lb_arrival);
    }

    public void setLb_weight(String lb_weight) {
        this.lb_weight.setText(lb_weight);
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

    public void setTicketTableView(List<TicketData> ticketTableView) {
        this.data.clear();
        this.data.setAll(ticketTableView);
    }
}
