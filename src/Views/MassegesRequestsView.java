package Views;

import Controllers.AController;
import Controllers.MassagesRequestsController;
import General.PurchaseMessage;
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
    public TableView tv_confirmationPurchase;
    public TableColumn col_ticketCommit;
    public TableColumn col_sellerCommit;
    public TableColumn col_ticketConfirm;
    public TableColumn col_buyer;
    public Button bn_confirm;
    public Button bn_orderNow;
    public Button bn_cancelConfirm;
    public Button bn_cancelCommit;




    private ObservableList<PurchaseMessage> dataCommit;
    private ObservableList<PurchaseMessage> dataConfirm;

    @Override
    public void setController(AController controller) {
        this.controller = (MassagesRequestsController) controller;
        this.controller.getCommitList();

    }
    public void initialize(){
        //
        this.dataCommit = FXCollections.observableArrayList();

        col_ticketCommit.setEditable(false);
        tv_commitPurchase.setRowFactory(param -> {TableRow<PurchaseMessage> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    bn_orderNow.setVisible(true);
                }
            });
            return row ;
        });

        col_ticketCommit.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("VacationCode")
        );
        col_sellerCommit.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("Seller_User")
        );


        this.tv_commitPurchase.setItems(dataCommit);

            //////// for table tv_confirmationPurchase
        this.dataConfirm = FXCollections.observableArrayList();

        tv_confirmationPurchase.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    bn_confirm.setVisible(true);
                    bn_cancelConfirm.setVisible(true);
                }
            });
            return row ;
        });

        col_ticketConfirm.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("VacationCode")
        );
        col_buyer.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("Purchase_User")
        );

        this.tv_confirmationPurchase.setItems(dataConfirm);

        if(controller!=null)
            this.controller.getCommitList();
    }


    public PurchaseMessage getSelectedOrderMessage(){
        return (PurchaseMessage)this.tv_commitPurchase.getSelectionModel().getSelectedItem();
    }

    public PurchaseMessage getSelectedConfirmMessage(){
        return (PurchaseMessage)this.tv_confirmationPurchase.getSelectionModel().getSelectedItem();
    }

    public void back(ActionEvent actionEvent){
        controller.back();
    }

    public void addToTableCommit(List<PurchaseMessage> tmp) {
        dataCommit.addAll(tmp);
    }
    public void addToTableConfirm(List<PurchaseMessage> tmp) {
        dataConfirm.addAll( tmp);
    }

    public void OrderNow(ActionEvent actionEvent) {
        this.controller.OrderNow(getSelectedOrderMessage());
        initialize();
    }

    public void Confirm(ActionEvent actionEvent) {
        this.controller.Confirm(getSelectedConfirmMessage());
        initialize();

    }



    public void cancelConfirm(ActionEvent actionEvent) {
        this.controller.cancelConfirm(getSelectedConfirmMessage());
    }
}
