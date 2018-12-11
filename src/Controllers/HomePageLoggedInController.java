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
        ArrayList<VacationData> tmp = new ArrayList<>();
        Map<String, VacationData> vacations = model.getVacations();
        tmp.addAll(vacations.values());

        this.view.addToTable(tmp);

    }

    public void Signout() {
        String UserName  = model.GetUserName();
        String Password = model.GetPassword();
        this.model.setSignOut(UserName, Password);
        SwapScene(PapaController.Views.HomePage);
    }

    public void openVacationInfoWindows() {
        VacationData v = this.view.getSelectedVacation();
        this.model.setSelectedVacationCode(v.getCode());
        SwapScene(PapaController.Views.VacationInfo);
    }
}
