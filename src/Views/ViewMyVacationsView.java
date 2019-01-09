package Views;

import Controllers.AController;
import Controllers.ViewMyVacationsController;
import General.VacationData;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Optional;

public class ViewMyVacationsView implements IView {



    private ViewMyVacationsController controller;

    @FXML
    public TableView tv_MyVacations;
    public TableColumn col_from;
    public TableColumn col_to;
    public TableColumn col_days;
    public TableColumn col_depart;
    public TableColumn col_travelers;
    public TableColumn col_price;
    public TableColumn col_vacationState;
    public RadioButton rb_forSale;
    public RadioButton rb_forExchange;
    public Button bn_change;


    private ObservableList<VacationData> data;

    @Override
    public void setController(AController controller) {
        this.controller = (ViewMyVacationsController) controller;
        initializeView();
    }

    public void initialize(){
        ToggleGroup group = new ToggleGroup();
        rb_forSale.setToggleGroup(group);
        rb_forExchange.setToggleGroup(group);



        this.data = FXCollections.observableArrayList();

        col_from.setEditable(false);
        tv_MyVacations.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    VacationData rowData = row.getItem();
                    this.controller.openChangeState();
                    this.setRadioButtons(rowData.getState());
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
                new PropertyValueFactory<VacationData,String>("VacationState")
        );

        this.tv_MyVacations.setItems(data);

        this.bn_change.disableProperty().bind(group.selectedToggleProperty().isNull());
    }

    private void initializeView() {
        this.controller.getMyVacations();
    }

    public VacationData getSelectedVacation() {
        return (VacationData) this.tv_MyVacations.getSelectionModel().getSelectedItem();
    }

    public void addToTable(ArrayList<VacationData> tmp) {

        data.addAll(tmp);
    }

    public void ChangeState(){

        if (rb_forSale.isSelected())
            this.controller.ChangeState(VacationData.State.valueOf("FOR_SELL"));
        if (rb_forExchange.isSelected())
            this.controller.ChangeState(VacationData.State.valueOf("FOR_EXCHANGE"));
    }

    public void setRadioButtons(String radioButtons) {
        if(radioButtons.equals("FOR_SELL"))
            rb_forSale.setSelected(true);
        else if (radioButtons.equals("FOR_EXCHANGE"))
            rb_forExchange.setSelected(true);

    }

    public void back(ActionEvent actionEvent) {
        this.controller.back();
    }
}
