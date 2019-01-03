package Views;

import Controllers.AController;
import Controllers.HomePageController;
import Controllers.HomePageLoggedInController;
import General.PapaController;
import General.VacationData;
import Models.HomePageLoggedinModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HomePageLoggedinView implements IView{

    private HomePageLoggedInController controller;

    public TableView tv_ForSellVacations;
    public TableColumn col_fromS;
    public TableColumn col_toS;
    public TableColumn col_daysS;
    public TableColumn col_departS;
    public TableColumn col_travelersS;
    public TableColumn col_priceS;

    private ObservableList<VacationData> ForSelldata;
    private ObservableList<VacationData> ForExchangedata;

    public Label lb_Gesture;
    public void initialize(){

        this.ForSelldata = FXCollections.observableArrayList();
        this.ForExchangedata = FXCollections.observableArrayList();

        tv_ForSellVacations.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    VacationData rowData = row.getItem();
                    this.controller.openVacationInfoWindows();
                }
            });
            return row ;
        });

        col_fromS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("From")
        );
        col_toS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("To")
        );
        col_daysS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Days")
        );
        col_departS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Depart")
        );
        col_travelersS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Travelers")
        );
        col_priceS.setCellValueFactory(
                new PropertyValueFactory<VacationData,Integer>("Price")
        );

        this.tv_ForSellVacations.setItems(ForSelldata);

        tv_ForSellVacations.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    VacationData rowData = row.getItem();
                    this.controller.openVacationInfoWindows();
                }
            });
            return row ;
        });

        col_fromS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("From")
        );
        col_toS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("To")
        );
        col_daysS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Days")
        );
        col_departS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Depart")
        );
        col_travelersS.setCellValueFactory(
                new PropertyValueFactory<VacationData,String>("Travelers")
        );
        col_priceS.setCellValueFactory(
                new PropertyValueFactory<VacationData,Integer>("Price")
        );

        this.tv_ForSellVacations.setItems(ForSelldata);
    }

    private void initializeView() {
        this.controller.getVacations();
    }

    public void setUserGesture(String str){
        this.lb_Gesture.setText(str);
    }

    @Override
    public void setController(AController controller) {

        this.controller = (HomePageLoggedInController) controller;
        this.controller.initializeView();
        initializeView();
    }


    public void addToSellTable(List<VacationData> vc){
        ForSelldata.addAll(vc);
    }
    public void addToExchangeTable(List<VacationData> vc){
        For.addAll(vc);
    }

    public VacationData getSelectedVacation() {
        return (VacationData) this.tv_ForSellVacations.getSelectionModel().getSelectedItem();
    }

    public void Signout(ActionEvent actionEvent){
        this.controller.Signout();
    }

    public void openCreateVecation(ActionEvent actionEvent) {
        this.controller.openCreateVacationWindow();
    }

    public void openViewMessages(ActionEvent actionEvent) {
        this.controller.openViewMessagesWindow();
    }

    public void mi_showReq (){
        this.controller.showReq();

    }

    public void openViewVacations(ActionEvent actionEvent) {
        this.controller.openViewVacations();

    }
}
