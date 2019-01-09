package Controllers;

import General.PapaController;
import General.PurchaseMessage;
import General.VacationData;
import Models.ExchangeModel;
import Models.PurchaseVacationModel;
import Models.UserModel;
import Views.IView;
import Views.MassegesRequestsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MassagesRequestsController extends AController {

    private PurchaseVacationModel model;
    private ExchangeModel exchangeModel;
    private MassegesRequestsView view;

    public MassagesRequestsController(PapaController papaController, PurchaseVacationModel model,ExchangeModel exchangeModel) {
        super(papaController);
        this.model = model;
        this.exchangeModel = exchangeModel;
    }

    @Override
    public void setView(IView view) {
            this.view = (MassegesRequestsView) view;

    }

    public void back() {
        SwapScene(PapaController.Views.HomePageLoggedIn);
    }

    public void getCommitList() {
        List <PurchaseMessage> CommitList = model.listOfSellers(UserModel.getUsername());
        List <PurchaseMessage> ConfirmList = model.listOfBuyersWithOneConfirm(UserModel.getUsername());
        List <PurchaseMessage> PaymentList = model.listOfBuyersWithTwoConfirm(UserModel.getUsername());

        this.view.addToTableCommit(CommitList);
        this.view.addToTableConfirm(ConfirmList);
        this.view.addToTablePayment(PaymentList);

    }

    public void confirmation(PurchaseMessage getSelectedConfirmMessage){
        this.model.updateOwner(getSelectedConfirmMessage.getPurchase_User(), getSelectedConfirmMessage.getVacationCode());
        this.model.removeRequest(getSelectedConfirmMessage.getVacationCode());
        this.model.setInvisible(getSelectedConfirmMessage.getVacationCode());

    }


    public void confirmEx(PurchaseMessage getSelectedConfirmMessage){

        VacationData vd = this.exchangeModel.getOfferedVacationData(getSelectedConfirmMessage.getPurchase_User(),getSelectedConfirmMessage.getVacationCode());
        this.model.updateOwner(getSelectedConfirmMessage.getSeller_User(), vd.getCode());
        this.model.updateOwner(getSelectedConfirmMessage.getPurchase_User(), getSelectedConfirmMessage.getVacationCode());
        this.exchangeModel.removeRequest(getSelectedConfirmMessage.getVacationCode());
        this.exchangeModel.removeRequest(vd.getCode());
        this.exchangeModel.setInvisible(vd.getCode());
        this.exchangeModel.setInvisible(getSelectedConfirmMessage.getVacationCode());
    }


    public void Confirm(PurchaseMessage getSelectedConfirmMessage) {
        this.model.setSeller(UserModel.getUsername());
        this.model.setBuyer(getSelectedConfirmMessage.getPurchase_User());
        this.model.setVacationCode(getSelectedConfirmMessage.getVacationCode());
        model.confirmVacationInDB(UserModel.getUsername(),getSelectedConfirmMessage.getPurchase_User(),getSelectedConfirmMessage.getVacationCode());

    }

    public void OrderNow(PurchaseMessage getSelectedOrderMessage) {
        boolean ifConfirmed = model.confirmation(getSelectedOrderMessage.getSeller_User(), UserModel.getUsername());
        this.model.setSeller(getSelectedOrderMessage.getSeller_User());
        this.model.setVacationCode(getSelectedOrderMessage.getVacationCode());
        this.model.setBuyer(getSelectedOrderMessage.getPurchase_User());
        this.model.updateBuyerConfirm(getSelectedOrderMessage.getSeller_User(),getSelectedOrderMessage.getPurchase_User(),getSelectedOrderMessage.getVacationCode());
        if(ifConfirmed)
            SwapScene(PapaController.Views.PurchesVacation);
    }


    public void cancelConfirm(PurchaseMessage selectedConfirmMessage) {
        VacationData vd = this.exchangeModel.getOfferedVacationData(selectedConfirmMessage.getPurchase_User(),selectedConfirmMessage.getVacationCode());
        this.exchangeModel.removeRequest(selectedConfirmMessage.getVacationCode(),selectedConfirmMessage.getSeller_User(),selectedConfirmMessage.getPurchase_User());
        this.exchangeModel.removeRequest(vd.getCode());
    }

    public void cancelConfirmP(PurchaseMessage selectedConfirmMessage) {
        this.model.removeRequest(selectedConfirmMessage.getSeller_User(),selectedConfirmMessage.getPurchase_User(),selectedConfirmMessage.getVacationCode());
    }


    public void cancelConfirmPay(PurchaseMessage selectedConfirmMessage) {
        this.model.removeRequest(selectedConfirmMessage.getSeller_User(),selectedConfirmMessage.getPurchase_User(),selectedConfirmMessage.getVacationCode());
    }

    public void getOfferedVacationData(String offer_user, int myVacationCode) {
        this.view.setOffredVacationData(this.exchangeModel.getOfferedVacationData(offer_user,myVacationCode));
    }

    public void getOfferedVacationsTrades() {

        List <PurchaseMessage> OffredTrades = exchangeModel.getofferings();
        this.view.setOfferedTrades(OffredTrades);
    }
}
