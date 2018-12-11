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
        this.view.setLb_airline(vd.getTicketData().get(0).getAirline());
        this.view.setLb_return(vd.getDays() + "");
        this.view.setLb_airline(vd.getDepart());
        this.view.setLb_from(vd.getFrom());
        this.view.setLb_height(vd.getTicketData().get(0).getLuggageData().getHeight() + "");
        this.view.setLb_weight(vd.getTicketData().get(0).getLuggageData().getWeight() + "");
        this.view.setLb_width(vd.getTicketData().get(0).getLuggageData().getWidth() + "");
        this.view.setLb_depart(vd.getDepart());
        this.view.setLb_hotelname(vd.getNightStayData().getHotelData().getCode());
        this.view.setLb_to(vd.getTo());
        this.view.setLb_vacationtype(vd.getVacationType());
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
