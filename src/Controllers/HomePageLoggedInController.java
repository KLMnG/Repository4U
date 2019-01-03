package Controllers;

import General.PapaController;
import General.VacationData;
import Models.HomePageLoggedinModel;
import Views.HomePageLoggedinView;
import Views.IView;

import java.util.ArrayList;
import java.util.Map;

public class HomePageLoggedInController extends AController{

    private HomePageLoggedinView view;
    private HomePageLoggedinModel model;


    public HomePageLoggedInController(PapaController papa, HomePageLoggedinModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (HomePageLoggedinView) view;
    }

    public void initializeView() {
        this.view.setUserGesture("♥ Hello " + this.model.getUserName() + " ♥");

    }
    public void getVacations() {
        this.view.addToSellTable(model.getVacationsByType(VacationData.State.FOR_SELL));
        this.view.addToExchangeTable(model.getVacationsByType(VacationData.State.FOR_EXCHANGE));
    }

    public void Signout() {
        String UserName  = model.GetUserName();
        String Password = model.GetPassword();
        this.model.setSignOut(UserName, Password);
        SwapScene(PapaController.Views.HomePage);
    }

    public void openCreateVacationWindow() {
        SwapScene(PapaController.Views.CreateVationWindow);
    }

    public void openViewMessagesWindow() {
        SwapScene(PapaController.Views.MassegesRequests);
    }

    public void openVacationInfoWindows() {
        VacationData v = this.view.getSelectedVacation();
        this.model.setSelectedVacationCode(v.getCode());
        SwapScene(PapaController.Views.VacationInfoLoggedIn);
    }

    public void showReq() {

        SwapScene(PapaController.Views.MassegesRequests);

    }

    public void openViewVacations() {
        SwapScene(PapaController.Views.ViewMyVacations);


    }
}
