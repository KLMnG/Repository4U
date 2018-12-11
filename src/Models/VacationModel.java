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
    private Map<Integer, VacationData> vacations;


    private List<String> vacation;
    private List<List<String>> tickets;
    private int selectedVacationCode;

    public VacationModel(UserModel userModel, ModelTicketDB modelTicketDB) {
        this.con = new DBConnection();
        this.userModel = userModel;
        this.modelTicketDB = modelTicketDB;
        this.vacation = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.vacations = new HashMap<Integer, VacationData>();
    }

    public void read() {
        String sql = "SELECT Tickets.code as ticketCode, flight_company " +
                ",departure_date, includes_flight_back, departure_from," +
                " destination, ticket_type, Users.Username as Username" +
                " , Users.Password as Password, Users.BirthDate as BirthDate," +
                " Users.FirstName as FirstName, Users.LastName as LastName," +
                " Users.City as City ,Luggages.weight as weight, Luggages.height as height, Luggages.width as width," +
                " Vacations.code as VacationCode, Vacations.time_to_stay as time_to_stay, Vacations.vacation_type as vacation_type, " +
                " Hotels.code as codeH ,Hotels.address as address, Hotels.rate as rate, price" +
                " FROM Tickets, Luggages,Vacations, Hotels, Users ";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                User seller = new User(rs.getString("Username"), "Password", "FirstName", "LastName", "City", "BirthDate");
                HotelData hotelData = new HotelData(rs.getString("codeH"), rs.getString("address"), rs.getString("rate"));
                LuggageData luggageData = new LuggageData(0, rs.getInt("weight"), rs.getInt("height"), rs.getInt("width"));
                NightStayData nightStayData = new NightStayData(0, hotelData);
                String vacationType = rs.getString("vacation_type");
                int price = rs.getInt("Price");

                TicketData ticketData = new TicketData(rs.getString("ticketCode"), rs.getString("departure_from"), rs.getString("destination"), rs.getString("departure_date"), rs.getString("flight_company"), luggageData, rs.getString("ticket_type"), rs.getInt("includes_flight_back"));

                if (!vacations.containsKey(rs.getInt("VacationCode"))) {
                    List<TicketData> lst = new ArrayList<TicketData>();
                    lst.add(ticketData);
                    VacationData vacationD = new VacationData(lst, nightStayData, price, vacationType, rs.getInt("VacationCode"), rs.getInt("time_to_stay"), seller);
                    vacations.put(rs.getInt("VacationCode"), vacationD);
                } else vacations.get(rs.getInt("VacationCode")).addToTicketData(ticketData);
            }


        } catch (SQLException e) {
        }
    }

    public Map<Integer, VacationData> getVacationData() {
        return this.vacations;
    }

    public VacationData getSelectedVacationData() {
        return this.vacations.get(this.selectedVacationCode);
    }

    public void addPassenger(String tf_timeToStay, String tf_vacationType, String cb_hotel, String tf_ticketNum, String tf_flightCompany, String tf_departueFrom, String cb_passangerType,
                             String cb_includeFlightBacl, String dp_flightDate, String tf_destination, CheckBox cb_luggage, String tf_weight, String tf_height, String tf_width, String tf_price) {

        if (vacation.size() == 0 || ((!tf_timeToStay.equals("")) || !vacation.get(0).equals(tf_timeToStay) || !vacation.get(1).equals(tf_vacationType) || !vacation.get(2).equals(cb_hotel))) {
            vacation = new ArrayList<>();
            vacation.add(tf_timeToStay);
            vacation.add(tf_vacationType);
            vacation.add(cb_hotel);

        }
        List<String> ticket = new ArrayList<>();
        ticket.add(tf_price);
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
        this.modelTicketDB.saveTickets(vacation, tickets);
        this.tickets = new ArrayList<>();
        this.vacation = new ArrayList<>();

    }

    public void setSelectedVacationCode(int selectedVacationCode) {
        this.selectedVacationCode = selectedVacationCode;
    }

    public List<String> getHotelsName() {

        List<String> hotelsName = null;

        String sql = "SELECT code FROM Hotels";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            hotelsName = new ArrayList<>();

            while (rs.next()) {
                hotelsName.add(rs.getString("code"));
            }
        } catch (SQLException e) {

        }
        return hotelsName;
    }
}
