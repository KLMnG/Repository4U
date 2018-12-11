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


    public void back()
    {
        SwapScene(PapaController.Views.HomePage);
    }
}
