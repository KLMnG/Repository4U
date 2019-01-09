package Views;

import Controllers.AController;
import Controllers.MassagesRequestsController;
import General.PurchaseMessage;
import General.VacationData;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.Binding;
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

    //exchange
    public TableView tv_VacationTradeOffers;
    public TableColumn col_VacationIDvo;
    public TableColumn col_SellerIDvo;

    public TableView tv_OfferedVacation;
    public TableColumn col_from;
    public TableColumn col_to;
    public TableColumn col_depart;
    public TableColumn col_days;
    public TableColumn col_travelers;

    public Button btn_ConfirmTrade;
    public Button btn_RejectTrade;

    private ObservableList<PurchaseMessage> TradeMessages;
    private ObservableList<PurchaseMessage> dataCommit;
    private ObservableList<PurchaseMessage> dataConfirm;
    private ObservableList<PurchaseMessage> dataPayment;



    @Override
    public void setController(AController controller) {
        this.controller = (MassagesRequestsController) controller;
        this.initializeView();
    }


    private void refreshTable(){
        this.controller.getCommitList();
        this.controller.getOfferedVacationsTrades();
        this.tv_OfferedVacation.getItems().clear();
    }

    private void initializeView(){
        refreshTable();
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




        col_VacationIDvo.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("VacationCode")
        );
        col_SellerIDvo.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("Purchase_User")
        );



        tv_VacationTradeOffers.setRowFactory(param -> {TableRow<PurchaseMessage> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    PurchaseMessage rowData = row.getItem();
                    this.controller.getOfferedVacationData(rowData.getPurchase_User(),rowData.getVacationCode());
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



        this.TradeMessages = FXCollections.observableArrayList();
        this.tv_VacationTradeOffers.setItems(TradeMessages);

        this.btn_ConfirmTrade.disableProperty().bind(this.tv_VacationTradeOffers.getSelectionModel().selectedItemProperty().isNull());
        this.btn_RejectTrade.disableProperty().bind(this.tv_VacationTradeOffers.getSelectionModel().selectedItemProperty().isNull());

        this.bn_confirmPurches.disableProperty().bind(this.tv_confirmationPurchase.getSelectionModel().selectedItemProperty().isNull());
        this.bn_cancelConfirm.disableProperty().bind(this.tv_confirmationPurchase.getSelectionModel().selectedItemProperty().isNull());

        this.bn_confirmPayment.disableProperty().bind(this.tv_PaymentConfirmation.getSelectionModel().selectedItemProperty().isNull());
        this.bn_cancelConfirm1.disableProperty().bind(this.tv_PaymentConfirmation.getSelectionModel().selectedItemProperty().isNull());

        this.bn_orderNow.disableProperty().bind(this.tv_commitPurchase.getSelectionModel().selectedItemProperty().isNull());

    }


    public PurchaseMessage getSelectedOrderMessage(){
        return (PurchaseMessage)this.tv_commitPurchase.getSelectionModel().getSelectedItem();
    }

    public PurchaseMessage getSelectedConfirmMessage(){
        return (PurchaseMessage)this.tv_VacationTradeOffers.getSelectionModel().getSelectedItem();
    }

    public PurchaseMessage getSelectedPaymentMessage(){
        return (PurchaseMessage)this.tv_PaymentConfirmation.getSelectionModel().getSelectedItem();
    }

    public PurchaseMessage getSelectedPaymentMessageEx(){
        return (PurchaseMessage)this.tv_VacationTradeOffers.getSelectionModel().getSelectedItem();
    }

    public void back(ActionEvent actionEvent){
        controller.back();
    }

    public void addToTableCommit(List<PurchaseMessage> tmp) {
        dataCommit.clear();
        dataCommit.addAll(tmp);
    }
    public void addToTableConfirm(List<PurchaseMessage> tmp) {
        dataConfirm.clear();
        dataConfirm.addAll(tmp);
    }

    public void OrderNow(ActionEvent actionEvent) {
        this.controller.OrderNow(getSelectedOrderMessage());
        refreshTable();
    }

    public void ConfirmPurches(ActionEvent actionEvent) {
        this.controller.Confirm((PurchaseMessage) tv_confirmationPurchase.getSelectionModel().getSelectedItem());
        refreshTable();
    }
    public void confirmPayment(){
        this.controller.confirmation(getSelectedPaymentMessage());
        refreshTable();
    }

    public void cancelConfirm(ActionEvent actionEvent) {
        this.controller.cancelConfirm(getSelectedConfirmMessage());
        refreshTable();
    }
    public void cancelConfirmP(ActionEvent actionEvent) {
        this.controller.cancelConfirmP((PurchaseMessage) tv_confirmationPurchase.getSelectionModel().getSelectedItem());
        refreshTable();
    }

    public void cancelConfirmPay(ActionEvent actionEvent) {
        this.controller.cancelConfirmPay((PurchaseMessage) tv_PaymentConfirmation.getSelectionModel().getSelectedItem());
        refreshTable();
    }


    public void setOffredVacationData(VacationData offredVacationData) {
        ObservableList<VacationData> tmp = FXCollections.observableArrayList();
        tmp.add(offredVacationData);
        this.tv_OfferedVacation.setItems(tmp);
    }

    public void setOfferedTrades(List<PurchaseMessage> offeredTrades) {
        this.TradeMessages.clear();
        this.TradeMessages.addAll(offeredTrades);
    }

    public void addToTablePayment(List<PurchaseMessage> paymentList) {
        this.dataPayment.clear();
        dataPayment.addAll(paymentList);

    }

    public void ExConfirmRequest(){
        this.controller.confirmEx(getSelectedPaymentMessageEx());
        refreshTable();

    }
}
