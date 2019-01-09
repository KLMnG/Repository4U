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

    @Override
    public void setController(AController controller) {
        this.controller = (MassagesRequestsController) controller;
        this.initializeView();

    }


    private void refreshTable(){
        this.controller.getCommitList();
        this.controller.getOfferedVacationsTrades();
    }

    private void initializeView(){
        refreshTable();
    }

    public void initialize(){

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




        col_ticketCommit.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("VacationCode")
        );
        col_sellerCommit.setCellValueFactory(
                new PropertyValueFactory<PurchaseMessage,String>("Seller_User")
        );



        tv_OfferedVacation.setRowFactory(param -> {TableRow<PurchaseMessage> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    PurchaseMessage rowData = row.getItem();
                    this.controller.getOfferedVacationData(rowData.getSeller_User(),rowData.getVacationCode());
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



        this.btn_ConfirmTrade.visibleProperty().bind(tv_OfferedVacation.selectionModelProperty());
        this.btn_RejectTrade.visibleProperty().bind(tv_OfferedVacation.selectionModelProperty());

        this.TradeMessages = FXCollections.observableArrayList();
        this.tv_VacationTradeOffers.setItems(TradeMessages);
    }


    public PurchaseMessage getSelectedOrderMessage(){
        return (PurchaseMessage)this.tv_commitPurchase.getSelectionModel().getSelectedItem();
    }

    public PurchaseMessage getSelectedConfirmMessage(){
        return (PurchaseMessage)this.tv_confirmationPurchase.getSelectionModel().getSelectedItem();
    }

    public void ConfirmPurches(){
        
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

    public void Confirm(ActionEvent actionEvent) {
        this.controller.Confirm(getSelectedConfirmMessage());
        this.controller.confirmation();
        refreshTable();
    }


    public void cancelConfirm(ActionEvent actionEvent) {
        this.controller.cancelConfirm(getSelectedConfirmMessage());
    }

    public void setOffredVacationData(VacationData offredVacationData) {
        ObservableList<VacationData> tmp = FXCollections.observableArrayList();
        tmp.add(offredVacationData);
        this.tv_OfferedVacation.setItems(tmp);
    }
}
