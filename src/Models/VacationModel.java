package Models;

import General.*;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class VacationModel {


    private DBConnection con;
    private UserModel userModel;
    private ModelTicketDB modelTicketDB;

    private VacationData vacationData;

    private List<String> vacation;
    private List<List<String>> tickets;

    public VacationModel(UserModel userModel, ModelTicketDB modelTicketDB) {
        this.userModel = userModel;
        this.modelTicketDB = modelTicketDB;
        this.vacation=new ArrayList<>();
        this.tickets=new ArrayList<>();
    }

    public void read() {

        List<TicketData> tickets = new ArrayList<TicketData>();

        Map<String,List<String>> vacations = null;
        String sql = "SELECT Tickets.code, flight_company,departure_date," +
                "departure_from, destination, ticket_type, seller, " +
                "Luggages.weight,Luggages.height,Luggages.width" +
                ",Vacations.time_to_stay, Vacations.vacation_type, " +
                ",Hotels.address, Hotels.rate, Price "
                + "FROM Tickets, Luggages, Vacation, Hotels ";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            ResultSet rs  = pstmt.executeQuery();

            vacations = new HashMap<>();

            String vacationType = null;
            int price = -1;
            User seller = null;
            HotelData hotelData = null;
            LuggageData luggageData = null;
            NightStayData nightStayData = null;

            while (rs.next()) {
                if (seller == null)
                    seller = new User(rs.getString("seller"),"","","","","");
                if (hotelData == null)
                    hotelData = new HotelData("",rs.getString("address"),rs.getString("rate"));
                if (luggageData == null)
                    luggageData = new LuggageData(0,rs.getInt("weight"),rs.getInt("height"),rs.getInt("width"));
                if (nightStayData == null)
                    nightStayData = new NightStayData(0,rs.getInt("time_to_stay"),hotelData);
                if (vacationType == null)
                    vacationType = rs.getString("vacation_type");
                if (price == -1)
                    price = rs.getInt("Price");
                tickets.add(new TicketData(rs.getString("departure_from"),rs.getString("destination"),rs.getString("departure_date"),rs.getString("flight_company"),luggageData,rs.getString("ticket_type"),seller));


            }

            this.vacationData = new VacationData(tickets,nightStayData,price,vacationType);


        } catch (SQLException e) {
        }
    }

    public VacationData getVacationData() {
        return vacationData;
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
