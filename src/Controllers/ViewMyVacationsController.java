package Controllers;

import General.PapaController;
import General.VacationData;
import Models.ViewMyVacationsModel;
import Views.IView;
import Views.ViewMyVacationsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ViewMyVacationsController extends AController {


    private ViewMyVacationsModel model;
    private ViewMyVacationsView view;

    public ViewMyVacationsController(PapaController papaController, ViewMyVacationsModel model) {
        super(papaController);
        this.model = model;
    }

    public void getMyVacations() {
        ArrayList<VacationData> tmp = new ArrayList<>();
        List< VacationData> vacations = model.getVacations();
        tmp.addAll(vacations);

        this.view.addToTable(tmp);

    }


    @Override
    public void setView(IView view) {
        this.view = (ViewMyVacationsView) view;
    }


    public void openChangeState() {

        VacationData v = this.view.getSelectedVacation();
        this.model.setSelectedVacationCode(v.getCode());


    }

    public void ChangeState(VacationData.State state) {
        VacationData vacationData  = this.model.getSelectedVacationCode();
        this.model.ChangeState(vacationData, state);
    }

    public void back() {
        SwapScene(PapaController.Views.HomePageLoggedIn);


    }


}
