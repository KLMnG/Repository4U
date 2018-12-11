package Views;

import Controllers.AController;
import Controllers.HomePageController;
import Controllers.HomePageLoggedInController;
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

    public TableView tv_vacations;
    public TableColumn col_from;
    public TableColumn col_to;
    public TableColumn col_days;
    public TableColumn col_depart;
    public TableColumn col_travelers;
    public TableColumn col_price;

    private ObservableList<VacationData> data;

    public Label lb_Gesture;
    public void initialize(){

        this.data = FXCollections.observableArrayList();

        col_from.setEditable(false);
        tv_vacations.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    VacationData rowData = row.getItem();
                    this.controller.openVacationInfoWindows();
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

        this.tv_vacations.setItems(data);
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


    public void addToTable(List<VacationData> vc){
        data.addAll(vc);
    }

    public VacationData getSelectedVacation() {
        return (VacationData) this.tv_vacations.getSelectionModel().getSelectedItem();
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
}
