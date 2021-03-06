package Controllers;

import General.HotelData;
import General.PapaController;
import General.TicketData;
import General.VacationData;
import Models.VacationModel;
import Views.CreateVacationsView;
import Views.IView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class CreateVacationsController extends AController {

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

    public void back() {
        SwapScene(PapaController.Views.HomePageLoggedIn);
    }

    public void saveTickets(VacationData vacationData) {
        model.saveTickets(vacationData);
    }

    public void swapScene() {
        SwapScene(PapaController.Views.HomePageLoggedIn);
    }

    public void initialize() {
        List<String> hotelNames = model.getHotelsName();
        view.getCb_hotel().getItems().addAll(hotelNames);
    }

    public boolean ValidateInput() {
        String message = "";

        if (view.getTf_price().getText().trim().isEmpty() || view.getTf_departueFrom().getText().trim().isEmpty() || view.getTf_ticketNum().getText().trim().isEmpty() ||
                view.getTf_flightCompany().getText().trim().isEmpty() || (view.dp_flightDate.getValue() != null && view.dp_flightDate.getValue().toString().trim().isEmpty()) || view.getTf_destination().getText().trim().isEmpty() || view.getCb_passangerType().getValue().toString().isEmpty())
            message += "Ticket Number,Passenger Type,Departure From,Flight Company,Flight Date, Flight Destination,Price must be filled\n";

        try {
            Integer.parseInt(view.getTf_price().getText());
            Integer.parseInt(view.getTf_timeToStay().getText());
        } catch (NumberFormatException e) {
            message += ("Price and Time to stay,must be numbers only\n");
        }

        if(view.getTf_timeToStay().getText().trim().isEmpty()){
            message +="must include time of stay";
        }


        if (this.view.cb_luggage.isSelected() && (this.view.tf_width.getText().trim().isEmpty() || this.view.tf_weight.getText().trim().isEmpty() || this.view.tf_height.getText().trim().isEmpty())) {
            message += "If Luggage is checked Height,Weight,Width must be filled\n";

            try {
                Integer.parseInt(view.getTf_weight().getText());
                Integer.parseInt(view.getTf_height().getText());
                Integer.parseInt(view.getTf_width().getText());

            } catch (NumberFormatException e) {
                message += ("Width,Height,Weight must be numbers only\n");
            }
        }

        try {
            if ( this.view.getDp_flightDate() == null || new SimpleDateFormat("dd/MM/yyyy").parse(this.view.getDp_flightDate().getEditor().getText()).before(new Date())) {
                message += "Ticket can't have an expiered date\n";
            }
        } catch (ParseException e) {

        }


        if (!message.isEmpty())
            this.view.ShowErrorAlert(message);

        return message.isEmpty();
    }
}
