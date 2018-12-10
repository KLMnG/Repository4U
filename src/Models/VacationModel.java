package Models;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class VacationModel {

    private UserModel userModel;
    private ModelTicketDB modelTicketDB;

    private List<String> vacation;
    private List<List<String>> tickets;

    public VacationModel(UserModel userModel, ModelTicketDB modelTicketDB) {
        this.userModel = userModel;
        this.modelTicketDB = modelTicketDB;
        this.vacation=new ArrayList<>();
        this.tickets=new ArrayList<>();
    }

    public void addPassenger(String tf_timeToStay, String tf_vacationType, String cb_hotel, String tf_ticketNum, String tf_flightCompany, String tf_departueFrom, String cb_passangerType,
                             String cb_includeFlightBacl,String dp_flightDate,String tf_destination,CheckBox cb_luggage, String tf_weight, String tf_height, String tf_width) {

        if ((vacation.size() == 0 && !tf_timeToStay.equals(""))|| !vacation.get(0).equals(tf_timeToStay) || !vacation.get(1).equals(tf_vacationType) || !vacation.get(2).equals(cb_hotel)) {
            vacation = new ArrayList<>();
            vacation.add(tf_timeToStay);
            vacation.add(tf_vacationType);
            vacation.add( cb_hotel);

        }
        List<String> ticket = new ArrayList<>();
        ticket.add(tf_ticketNum);
        ticket.add(tf_flightCompany);
        ticket.add(tf_departueFrom);
        ticket.add(cb_passangerType);
        ticket.add(cb_includeFlightBacl);
        ticket.add(dp_flightDate);
        ticket.add(tf_destination);
        if (cb_luggage.isSelected()) {
            ticket.add(tf_weight);
            ticket.add(tf_height);
            ticket.add(tf_width);
        }
        tickets.add(ticket);
    }


    public void saveTickets() {
        modelTicketDB.saveTickets(vacation,tickets);

    }

}
