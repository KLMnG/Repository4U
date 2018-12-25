package Controllers;

import General.PapaController;
import General.VacationData;
import Models.VacationInfoLoggedinModel;
import Views.IView;
import Views.VacationInfoLoggedinView;

public class VacationInfoLoggedinController extends AController{


    private VacationInfoLoggedinView view;
    private VacationInfoLoggedinModel model;

    public VacationInfoLoggedinController(PapaController papa,VacationInfoLoggedinModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (VacationInfoLoggedinView) view;
    }

    public void initializeView() {
        VacationData vd = this.model.getSelectedVacationData();
        this.view.setTicketTableView(vd.getTicketData());
        this.view.setLb_hotelname(vd.getHotel().getCode());
        this.view.setLb_address(vd.getHotel().getAddress());
        this.view.setLb_rate(vd.getHotel().getRate());
        this.view.setLb_vacationtype(vd.getVacationType());
        this.view.setLb_timetostay(vd.getDays() + "");
        this.view.setLb_vacationPrice(vd.getPrice());
    }

    public void PurchaseVacation() {
        String message = "Purchase vacation permition added successfully";
        if (!this.model.AskToPurchase())
            message = "Error while trying to add request to database";
        this.view.ShowInfoAlert(message);
        SwapScene(PapaController.Views.HomePageLoggedIn);
    }

    public void back()
    {
        SwapScene(PapaController.Views.HomePageLoggedIn);
    }
}
