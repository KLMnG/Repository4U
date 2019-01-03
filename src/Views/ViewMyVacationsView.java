package Views;

import Controllers.AController;
import Controllers.ViewMyVacationsController;
import General.VacationData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ViewMyVacationsView implements IView {



    private ViewMyVacationsController controller;

    @FXML
    public TableView tv_vacations;
    public TableColumn col_from;
    public TableColumn col_to;
    public TableColumn col_days;
    public TableColumn col_depart;
    public TableColumn col_travelers;
    public TableColumn col_price;
    public TableColumn col_vacationState;

    private ObservableList<VacationData> data;

    @Override
    public void setController(AController controller) {
        this.controller = (ViewMyVacationsController) controller;
        initializeView();
    }

    public void initialize(){

        this.data = FXCollections.observableArrayList();

        col_from.setEditable(false);
        tv_vacations.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    VacationData rowData = row.getItem();
                    this.controller.openChangeState();

                }
            });
            return row ;
        });

        col_from.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("From")
        );
        col_to.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("To")
        );
        col_days.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Days")
        );
        col_depart.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Depart")
        );
        col_travelers.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Travelers")
        );
        col_price.setCellValueFactory(
                new PropertyValueFactory<VacationData,Integer>("Price")
        );
        col_vacationState.setCellValueFactory(
                new PropertyValueFactory<VacationData,Integer>("Vacation state")
        );


        this.tv_vacations.setItems(data);
    }

    private void initializeView() {
        this.controller.getMyVacations();
    }

    public VacationData getSelectedVacation() {
        return (VacationData) this.tv_vacations.getSelectionModel().getSelectedItem();
    }

    public void addToTable(ArrayList<VacationData> tmp) {

        data.addAll(tmp);
    }

    public void ChangeStateToSale(){
        this.controller.ChangeState("for sale");
    }
}
