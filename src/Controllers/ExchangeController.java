package Controllers;

import General.PapaController;
import General.VacationData;
import Models.ExchangeModel;
import Views.IView;
import Views.VacationInfoLoggedinExchangeView;

public class ExchangeController extends AController{

    private VacationInfoLoggedinExchangeView view;
    private ExchangeModel model;

    public ExchangeController(PapaController papa, ExchangeModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (VacationInfoLoggedinExchangeView) view;
    }

    public void initializeView() {
        VacationData vd = this.model.getSelectedVacationData();
        initializeUserVacations();
        this.view.setTicketTableView(vd.getTicketData());
        this.view.setLb_hotelname(vd.getHotel().getCode());
        this.view.setLb_address(vd.getHotel().getAddress());
        this.view.setLb_rate(vd.getHotel().getRate());
        this.view.setLb_vacationtype(vd.getVacationType());
        this.view.setLb_timetostay(vd.getDays() + "");
        this.view.setLb_vacationPrice(vd.getPrice());
    }

    private void initializeUserVacations(){
        this.view.setMyVacationTableView(this.model.getUserExchangeVacations());
    }



    public void ExchangeVacation(VacationData vacation) {
        String message = "Exchange vacation permition added successfully";
        if (!this.model.AskToExchange(vacation))
            message = "Error while trying to add request to database";
        this.view.ShowInfoAlert(message);
        SwapScene(PapaController.Views.HomePageLoggedIn);
    }

    public void back()
    {
        SwapScene(PapaController.Views.HomePageLoggedIn);
    }
}
