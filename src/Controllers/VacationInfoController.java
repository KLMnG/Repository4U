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
        this.view.setLb_hotelname(vd.getHotel().getCode());
        this.view.setLb_address(vd.getHotel().getAddress());
        this.view.setLb_rate(vd.getHotel().getRate());
        this.view.setLb_vacationtype(vd.getVacationType());
        this.view.setLb_timetostay(vd.getDays() + "");
    }


    public void back()
    {
        SwapScene(PapaController.Views.HomePage);
    }

}
