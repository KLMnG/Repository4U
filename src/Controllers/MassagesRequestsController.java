package Controllers;

import General.PapaController;
import General.PurchaseMessage;
import General.VacationData;
import Models.PurchaseVacationModel;
import Models.UserModel;
import Views.IView;
import Views.MassegesRequestsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MassagesRequestsController extends AController {

    private PurchaseVacationModel model;
    private MassegesRequestsView view;

    public MassagesRequestsController(PapaController papaController, PurchaseVacationModel model) {
        super(papaController);
        this.model = model;
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
        List <PurchaseMessage> ConfirmList = model.listOfBuyers(UserModel.getUsername());

        this.view.addToTableCommit(CommitList);
        this.view.addToTableConfirm(ConfirmList);

    }



    public void openVacationInfoWindows() {


    }

    public void Confirm(PurchaseMessage getSelectedConfirmMessage) {
        model.confirmVacationInDB(UserModel.getUsername(),getSelectedConfirmMessage.getPurchase_User(),getSelectedConfirmMessage.getVacationCode());

    }

    public void OrderNow(PurchaseMessage getSelectedOrderMessage) {
        boolean ifConfirmed = model.confirmation(getSelectedOrderMessage.getSeller_User(), UserModel.getUsername());
        if(ifConfirmed)
            SwapScene(PapaController.Views.PurchesVacation);
    }
}
