package Controllers;

import General.PapaController;
import General.VacationData;
import Models.PurchaseVacationModel;
import Models.UserModel;
import Views.IView;
import Views.MassegesRequestsView;

import java.util.ArrayList;
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
        ArrayList<String> tmp = new ArrayList<>();
        Map<String, String> CommitList = model.listOfBuyers(UserModel.getUsername());
        tmp.addAll(CommitList.values());

        this.view.addToTable(tmp);

    }



    public void openVacationInfoWindows() {




    }

    public void Confirm() {
        model.confirmVacationInDB(UserModel.getUsername(),"","");


    }

    public void OrderNow() {
        boolean ifConfirmed = model.confirmation("", UserModel.getUsername());
        if(ifConfirmed)
            SwapScene(PapaController.Views.PurchesVacation);
    }
}
