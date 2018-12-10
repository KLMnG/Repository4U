package Controllers;

import General.PapaController;
import Models.VacationModel;
import Views.IView;
import Views.VacationInfoView;


public class VacationInfoController extends AController {


    private VacationInfoView view;
    private VacationModel model;

    public VacationInfoController(PapaController papa, VacationModel vacationModel) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (VacationInfoView) view;
    }

    public void initializeView() {
        model.read();
        model.getVacationData();
        //model
    }

    public void back()
    {
        SwapScene(PapaController.Views.HomePage);
    }
}
