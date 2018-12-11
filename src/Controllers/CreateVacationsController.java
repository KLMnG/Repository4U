package Controllers;

import General.PapaController;
import Models.VacationModel;
import Views.CreateVacationsView;
import Views.IView;

import java.util.ArrayList;
import java.util.List;

public class CreateVacationsController extends AController{

    private CreateVacationsView view;
    private VacationModel model;
    public CreateVacationsController(PapaController papa, VacationModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (CreateVacationsView) view;
    }


    public void addVacation(List<String> vacation){

    }

    public void saveTickets() {
        model.saveTickets();

    }

    public void notifyPassengerAdded() {

        model.addPassenger(view.getTf_timeToStay().getText(), view.getTf_vacationType().getText(), view.getCb_hotel().getEditor().getText(), view.getTf_ticketNum().getText(), view.getTf_flightCompany().getText(),
                view.getTf_departueFrom().getText(), view.getCb_passangerType().getEditor().getText(),
                view.getCb_includeFlightBacl().getText(),view.getDp_flightDate().getValue().toString()
                ,view.getTf_destination().getText(), view.getCb_luggage(), view.getTf_weight().getText(), view.getTf_height().getText(), view.getTf_width().getText(),view.getTf_price().getText());



    }
}
