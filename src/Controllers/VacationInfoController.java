package Controllers;

import General.PapaController;
import General.VacationData;
import Models.VacationModel;
import Views.IView;
import Views.VacationInfoView;


public class VacationInfoController extends AController {


    private VacationInfoView view;
    private VacationModel model;

    public VacationInfoController(PapaController papa, VacationModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (VacationInfoView) view;
    }

    public void initializeView() {
        VacationData vd = this.model.getSelectedVacationData();
        this.view.setTicketTableView(vd.getTicketData());
    }


    public void back()
    {
        SwapScene(PapaController.Views.HomePage);
    }

}
