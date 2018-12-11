package Controllers;

import General.PapaController;
import General.VacationData;
import Models.HomePageModel;
import Views.HomePageView;
import Views.IView;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HomePageController extends AController {


    private HomePageView view;
    private HomePageModel model;

    public HomePageController(PapaController papa,HomePageModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (HomePageView) view;

    }

    public void openLoginWindow() {
        SwapScene(PapaController.Views.LoginWindow);
    }

    public void openRegisterWindow() {
        SwapScene(PapaController.Views.RegisterWindow);
    }


    public void getVacations() {
        ArrayList<VacationData> tmp = new ArrayList<>();
        Map <String, VacationData> vacations = model.getVacations();
        tmp.addAll(vacations.values());

       this.view.addToTable(tmp);

    }


    public void openVacationInfoWindows() {
        VacationData v = this.view.getSelectedVacation();
        this.model.setSelectedVacationCode(v.getCode());
        SwapScene(PapaController.Views.VacationInfo);

    }
}
