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
    public TableView tv_PaymentConfirmation;
    public TableColumn col_ticketCommit;
    public TableColumn col_sellerCommit;
    public TableColumn col_ticketConfirm;
    public TableColumn col_buyer;
    public TableColumn col_buyer1;
    public TableColumn col_ticketConfirm1;


    public Button bn_confirmPurches;
    public Button bn_orderNow;
    public Button bn_cancelConfirm;
    public Button bn_confirmPayment;
    public Button bn_cancelConfirm1;




    private ObservableList<PurchaseMessage> dataCommit;
    private ObservableList<PurchaseMessage> dataConfirm;
    private ObservableList<PurchaseMessage> dataPayment;



    @Override
    public void setController(AController controller) {
        this.controller = (MassagesRequestsController) controller;
        this.controller.getCommitList();

    }
    public void initialize(){
        // for table tv_commitPurchase
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
                    bn_confirmPurches.setVisible(true);
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





        ///for table tv_PaymentConfirmation

        this.dataPayment = FXCollections.observableArrayList();

        tv_PaymentConfirmation.setRowFactory(param -> {TableRow<VacationData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    bn_confirmPayment.setVisible(true);
                    bn_cancelConfirm1.setVisible(true);
                }
            });
            return row ;
        });

        col_ticketConfirm1.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("VacationCode")
        );
        col_buyer1.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("Purchase_User")
        );

        this.tv_PaymentConfirmation.setItems(dataPayment);

        if(controller!=null)
            this.controller.getCommitList();

    }


    public PurchaseMessage getSelectedOrderMessage(){
        return (PurchaseMessage)this.tv_commitPurchase.getSelectionModel().getSelectedItem();
    }

    public PurchaseMessage getSelectedConfirmMessage(){
        return (PurchaseMessage)this.tv_confirmationPurchase.getSelectionModel().getSelectedItem();
    }

    public PurchaseMessage getSelectedPaymentMessage(){
        return (PurchaseMessage)this.tv_PaymentConfirmation.getSelectionModel().getSelectedItem();
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

    public void ConfirmPurches(ActionEvent actionEvent) {
        this.controller.Confirm(getSelectedConfirmMessage());
        initialize();

    }
    public void confirmPayment(){
        this.controller.confirmation(getSelectedPaymentMessage());
        initialize();

    }



    public void cancelConfirm(ActionEvent actionEvent) {
        this.controller.cancelConfirm(getSelectedConfirmMessage());
    }

    public void addToTablePayment(List<PurchaseMessage> paymentList) {

        dataPayment.addAll(paymentList);

    }
}
