package Controllers;

import General.PapaController;
import Models.VacationInfoModel;
import Views.IView;
import Views.RegisterView;
import Views.VacationInfoView;


public class VacationInfoController extends AController {


    private VacationInfoView view;
    private VacationInfoModel model;
    public VacationInfoController(PapaController papa) {
        super(papa);
    }

    @Override
    public void setView(IView view) {
        this.view = (VacationInfoView) view;

    }
}
