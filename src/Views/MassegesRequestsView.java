package Views;

import Controllers.AController;
import Controllers.MassagesRequestsController;
import General.VacationData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;


public class MassegesRequestsView implements IView{

    private MassagesRequestsController controller;
    @FXML
    public TableView tv_commitPurchase;
    @FXML
    public TableView tv_confirmationPurchase;
    @FXML
    public TableColumn col_ticketCommit;
    public TableColumn col_sellerCommit;
    public TableColumn col_ticketConfirm;
    public TableColumn col_buyer;
    public Button bn_confirm;
    public Button bn_orderNow;

    private ObservableList<String> dataCommit;
    private ObservableList<String> dataConfirm;

    @Override
    public void setController(AController controller) {
        this.controller = (MassagesRequestsController) controller;
        this.controller.getCommitList();

    }
    public void initialize(){
        //
        this.dataCommit = FXCollections.observableArrayList();

        col_ticketCommit.setEditable(false);
        tv_commitPurchase.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    VacationData rowData = row.getItem();
                    bn_orderNow.setVisible(true);


                    this.controller.openVacationInfoWindows();
                }
            });
            return row ;
        });

        col_ticketCommit.setCellValueFactory(
                new PropertyValueFactory<String,String>("Ticket Code")
        );
        col_sellerCommit.setCellValueFactory(
                new PropertyValueFactory<String,String>("Seller user name")
        );


        this.tv_commitPurchase.setItems(dataCommit);

            //////// for table tv_confirmationPurchase
        this.dataConfirm = FXCollections.observableArrayList();

        col_ticketConfirm.setEditable(false);
        tv_confirmationPurchase.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    VacationData rowData = row.getItem();
                    bn_confirm.setVisible(true);


                }
            });
            return row ;
        });

        col_ticketConfirm.setCellValueFactory(
                new PropertyValueFactory<String,String>("Ticket Code")
        );
        col_buyer.setCellValueFactory(
                new PropertyValueFactory<String,String>("Seller user name")
        );


        this.tv_confirmationPurchase.setItems(dataConfirm);





    }
    public void OrderNow(){
        this.controller.OrderNow();
    }


    public void Confirm(){
        this.controller.Confirm();
    }




    public void back(ActionEvent actionEvent){
        controller.back();
    }

    public void addToTable(List<String> tmp) {
        dataCommit.addAll(tmp);
    }
}
