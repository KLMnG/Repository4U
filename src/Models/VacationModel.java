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

    private int selectedVacationCode;
    private int SelectedVacationForChangeCode;

    public VacationModel(UserModel userModel, ModelTicketDB modelTicketDB) {
        this.con = new DBConnection();
        this.userModel = userModel;
        this.modelTicketDB = modelTicketDB;
        this.vacations = new HashMap<>();
}

    public void read() {
        this.vacations = new HashMap<>();

        String sql = "SELECT Tickets.code as ticketCode, flight_company ,departure_date, includes_flight_back, departure_from, destination, ticket_type, weight, height, width, \n" +
                "Users.Username as Username, Users.Password as Password, Users.BirthDate as BirthDate,Users.FirstName as FirstName, Users.LastName as LastName, Users.City as City,\n" +
                "Vacations.code as VacationCode, Vacations.time_to_stay as time_to_stay, Vacations.vacation_type as vacation_type, Vacations.hotel as hotel, Vacations.owner as owner, Vacations.price as price, Vacations.state as state, \n" +
                "Hotels.code as hotelName ,Hotels.address as address, Hotels.rate as rate\n" +
                "FROM Tickets\n" +
                "left join Vacations on Vacations.code = Tickets.vacation\n" +
                "left join Hotels on Hotels.code = Vacations.hotel\n" +
                "left join Users on Vacations.owner = Users.Username ";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User seller = new User(rs.getString("Username"), "Password", "FirstName", "LastName", "City", "BirthDate");
                String vacationType = rs.getString("vacation_type");
                int price = rs.getInt("price");

                TicketData ticketData = new TicketData(rs.getString("ticketCode"), rs.getString("departure_from"), rs.getString("destination"),
                        rs.getString("departure_date"), rs.getString("flight_company"), rs.getInt("weight"),
                        rs.getInt("height"),rs.getInt("width"), rs.getString("ticket_type"),
                        rs.getInt("includes_flight_back"),rs.getInt("VacationCode"));

                if (!vacations.containsKey(rs.getInt("VacationCode"))) {
                    List<TicketData> lst = new ArrayList<>();
                    lst.add(ticketData);
                    HotelData hotelData = new HotelData(rs.getString("hotelName"),rs.getString("address"),rs.getString("rate"));
                    VacationData vacationD = new VacationData(lst,price, vacationType, rs.getInt("VacationCode"), rs.getInt("time_to_stay"), seller,hotelData);
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

    public void saveTickets(VacationData vacationData) {
        this.modelTicketDB.saveTickets(vacationData);
    }

    public void setSelectedVacationCode(int selectedVacationCode) {
        this.selectedVacationCode = selectedVacationCode;
    }
    public void setSelectedVacationForChangeCode(int selectedVacationCode) {
        this.SelectedVacationForChangeCode = selectedVacationCode;
    }

    public VacationData getSelectedVacationForChangeCode(){
        return this.vacations.get(this.SelectedVacationForChangeCode);
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

    public List<VacationData> getAllVacationByType(String user,VacationData.State state){

        this.vacations = new HashMap<>();
        List<VacationData> allVacation= new ArrayList<>();;
        VacationData vacationD;
        String sql = "SELECT Tickets.code as ticketCode, flight_company ,departure_date, includes_flight_back, departure_from, destination, ticket_type, weight, height, width, \n" +
                "Users.Username as Username, Users.Password as Password, Users.BirthDate as BirthDate,Users.FirstName as FirstName, Users.LastName as LastName, Users.City as City,\n" +
                "Vacations.code as VacationCode, Vacations.time_to_stay as time_to_stay, Vacations.vacation_type as vacation_type, Vacations.hotel as hotel, Vacations.owner as owner, Vacations.price as price, Vacations.state as state, \n" +
                "Hotels.code as hotelName ,Hotels.address as address, Hotels.rate as rate\n" +
                "FROM Tickets\n" +
                "left join Vacations on Vacations.code = Tickets.vacation\n" +
                "left join Hotels on Hotels.code = Vacations.hotel\n" +
                "left join Users on Vacations.owner = Users.Username\n" +
                "WHERE NOT Vacations.owner = ? AND Vacation.state = ?";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,user);
            pstmt.setString(2,state.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User seller = new User(rs.getString("Username"), "Password", "FirstName", "LastName", "City", "BirthDate");
                String vacationType = rs.getString("vacation_type");
                int price = rs.getInt("price");

                TicketData ticketData = new TicketData(rs.getString("ticketCode"), rs.getString("departure_from"), rs.getString("destination"),
                        rs.getString("departure_date"), rs.getString("flight_company"), rs.getInt("weight"),
                        rs.getInt("height"),rs.getInt("width"), rs.getString("ticket_type"),
                        rs.getInt("includes_flight_back"),rs.getInt("VacationCode"));

                if (!vacations.containsKey(rs.getInt("VacationCode"))) {
                    List<TicketData> lst = new ArrayList<>();
                    lst.add(ticketData);
                    HotelData hotelData = new HotelData(rs.getString("hotelName"),rs.getString("address"),rs.getString("rate"));
                    vacationD = new VacationData(lst,price, vacationType, rs.getInt("VacationCode"), rs.getInt("time_to_stay"), seller,hotelData);
                    allVacation.add(vacationD);
                    vacations.put(rs.getInt("VacationCode"), vacationD);
                } else vacations.get(rs.getInt("VacationCode")).addToTicketData(ticketData);

            }


        } catch (SQLException e) {
        }
        return allVacation;
    }

    public void setNewStateForVacation(String vacationCode, VacationData.State newState){

        String sql = "UPDATE Vacations SET state = ? "+
                "WHERE code_vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newState.toString());
            pstmt.setString(2, vacationCode);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        }



    }

    public void changeOwnersVacations(String user1,String codeNewVacation1, String user2, String codeNewVacation2){
        changeOwnerVacation(user1,codeNewVacation1);
        changeOwnerVacation(user2,codeNewVacation2);
    }

    private void changeOwnerVacation(String user1, String code) {

        String sql = "UPDATE Vacations SET owner = ? "+
                "WHERE code_vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user1);
            pstmt.setString(2, code);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
}
