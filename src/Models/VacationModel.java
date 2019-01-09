package Models;

import General.*;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class VacationModel {


    private DBConnection con;
    private UserModel userModel;
    private ModelTicketDB modelTicketDB;

    private int selectedVacationCode;
    private int SelectedVacationForChangeCode;

    public VacationModel(UserModel userModel, ModelTicketDB modelTicketDB) {
        this.con = new DBConnection();
        this.userModel = userModel;
        this.modelTicketDB = modelTicketDB;
    }

    public List<VacationData> read() {

        Map<Integer, VacationData> vacations = new HashMap<>();
        String sql = "SELECT Tickets.code as ticketCode, flight_company ,departure_date, includes_flight_back, departure_from, destination, ticket_type, weight, height, width, \n" +
                "Users.Username as Username, Users.Password as Password, Users.BirthDate as BirthDate,Users.FirstName as FirstName, Users.LastName as LastName, Users.City as City,\n" +
                "Vacations.code as VacationCode, Vacations.time_to_stay as time_to_stay, Vacations.vacation_type as vacation_type, Vacations.hotel as hotel, Vacations.owner as owner, Vacations.price as price, \n" +
                "Vacations.state as VacationState, Hotels.code as hotelName ,Hotels.address as address, Hotels.rate as rate\n" +
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
                        rs.getInt("height"), rs.getInt("width"), rs.getString("ticket_type"),
                        rs.getInt("includes_flight_back"), rs.getInt("VacationCode"));

                if (!vacations.containsKey(rs.getInt("VacationCode"))) {
                    List<TicketData> lst = new ArrayList<>();
                    lst.add(ticketData);
                    HotelData hotelData = new HotelData(rs.getString("hotelName"), rs.getString("address"), rs.getString("rate"));
                    VacationData.State state = VacationData.State.valueOf(rs.getString("VacationState"));
                    VacationData vacationD = new VacationData(lst, price, vacationType, rs.getInt("VacationCode"), rs.getInt("time_to_stay"), seller, hotelData, state);
                    vacations.put(rs.getInt("VacationCode"), vacationD);
                } else vacations.get(rs.getInt("VacationCode")).addToTicketData(ticketData);
            }


        } catch (SQLException e) {
        }
        return new ArrayList<VacationData>(vacations.values());
    }

    public VacationData readByID(int vacationID) {

        VacationData vacation = null;
        String sql = "SELECT Tickets.code as ticketCode, flight_company ,departure_date, includes_flight_back, departure_from, destination, ticket_type, weight, height, width, \n" +
                "Users.Username as Username, Users.Password as Password, Users.BirthDate as BirthDate,Users.FirstName as FirstName, Users.LastName as LastName, Users.City as City,\n" +
                "Vacations.code as VacationCode, Vacations.time_to_stay as time_to_stay, Vacations.vacation_type as vacation_type, Vacations.hotel as hotel, Vacations.owner as owner, Vacations.price as price, Vacations.state as VacationState, \n" +
                "Hotels.code as hotelName ,Hotels.address as address, Hotels.rate as rate\n" +
                "FROM Tickets\n" +
                "left join Vacations on Vacations.code = Tickets.vacation\n" +
                "left join Hotels on Hotels.code = Vacations.hotel\n" +
                "left join Users on Vacations.owner = Users.Username\n" +
                "where VacationCode = ?";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vacationID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User seller = new User(rs.getString("Username"), "Password", "FirstName", "LastName", "City", "BirthDate");
                String vacationType = rs.getString("vacation_type");
                int price = rs.getInt("price");

                TicketData ticketData = new TicketData(rs.getString("ticketCode"), rs.getString("departure_from"), rs.getString("destination"),
                        rs.getString("departure_date"), rs.getString("flight_company"), rs.getInt("weight"),
                        rs.getInt("height"), rs.getInt("width"), rs.getString("ticket_type"),
                        rs.getInt("includes_flight_back"), rs.getInt("VacationCode"));


                if (vacation == null) {
                    List<TicketData> lst = new ArrayList<>();
                    lst.add(ticketData);
                    VacationData.State state = VacationData.State.valueOf(rs.getString("VacationState"));
                    HotelData hotelData = new HotelData(rs.getString("hotelName"), rs.getString("address"), rs.getString("rate"));
                    vacation = new VacationData(lst, price, vacationType, rs.getInt("VacationCode"), rs.getInt("time_to_stay"), seller, hotelData, state);
                } else vacation.addToTicketData(ticketData);
            }


        } catch (SQLException e) {
        }
        return vacation;
    }

    public List<VacationData> getVacationData() {
        return read();
    }

    public VacationData getSelectedVacationData() {
        return this.readByID(this.selectedVacationCode);
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

    public VacationData getSelectedVacationForChangeCode() {
        return this.readByID(SelectedVacationForChangeCode);
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

    public List<VacationData> getAllVacationByType(String user, VacationData.State state) {

        Map<Integer, VacationData> vacations = new HashMap<>();
        VacationData vacationD;
        String sql = "SELECT Tickets.code as ticketCode, flight_company ,departure_date, includes_flight_back, departure_from, destination, ticket_type, weight, height, width, \n" +
                "Users.Username as Username, Users.Password as Password, Users.BirthDate as BirthDate,Users.FirstName as FirstName, Users.LastName as LastName, Users.City as City,\n" +
                "Vacations.code as VacationCode, Vacations.time_to_stay as time_to_stay, Vacations.vacation_type as vacation_type, Vacations.hotel as hotel, Vacations.owner as owner, Vacations.price as price, Vacations.state as VacationState, \n" +
                "Hotels.code as hotelName ,Hotels.address as address, Hotels.rate as rate\n" +
                "FROM Tickets\n" +
                "left join Vacations on Vacations.code = Tickets.vacation\n" +
                "left join Hotels on Hotels.code = Vacations.hotel\n" +
                "left join Users on Vacations.owner = Users.Username\n" +
                "WHERE NOT Vacations.owner = ? AND Vacations.state = ?";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, state.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User seller = new User(rs.getString("Username"), "Password", "FirstName", "LastName", "City", "BirthDate");
                String vacationType = rs.getString("vacation_type");
                int price = rs.getInt("price");

                TicketData ticketData = new TicketData(rs.getString("ticketCode"), rs.getString("departure_from"), rs.getString("destination"),
                        rs.getString("departure_date"), rs.getString("flight_company"), rs.getInt("weight"),
                        rs.getInt("height"), rs.getInt("width"), rs.getString("ticket_type"),
                        rs.getInt("includes_flight_back"), rs.getInt("VacationCode"));

                if (!vacations.containsKey(rs.getInt("VacationCode"))) {
                    List<TicketData> lst = new ArrayList<>();
                    lst.add(ticketData);
                    HotelData hotelData = new HotelData(rs.getString("hotelName"), rs.getString("address"), rs.getString("rate"));
                    vacationD = new VacationData(lst, price, vacationType, rs.getInt("VacationCode"), rs.getInt("time_to_stay"), seller, hotelData, state);
                    //allVacation.add(vacationD);
                    vacations.put(rs.getInt("VacationCode"), vacationD);
                } else vacations.get(rs.getInt("VacationCode")).addToTicketData(ticketData);

            }


        } catch (SQLException e) {
        }
        return new ArrayList<VacationData>(vacations.values());
    }

    public void setNewStateForVacation(int vacationCode, VacationData.State newState) {

        String sql = "UPDATE Vacations SET state = ? " +
                "WHERE code = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newState.toString());
            pstmt.setInt(2, vacationCode);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        }


    }

    public void changeOwnersVacations(String user1, String codeNewVacation1, String user2, String codeNewVacation2) {
        changeOwnerVacation(user1, codeNewVacation1);
        changeOwnerVacation(user2, codeNewVacation2);
    }

    private void changeOwnerVacation(String user1, String code) {

        String sql = "UPDATE Vacations SET owner = ? " +
                "WHERE code_vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user1);
            pstmt.setString(2, code);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    ///// for exchange ///////////
    public void addNewExchange(String user1, String user2, int codeVacation1, int codeVacation2) {
        String sql = "INSERT INTO Exchange(user1,user2,code1,code2) VALUES(?,?,?,?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user1);
            pstmt.setString(2, user2);
            pstmt.setInt(3, codeVacation1);
            pstmt.setInt(4, codeVacation2);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public List<PurchaseMessage> getRequestingOffers(String user) {
        List<PurchaseMessage> ans = new ArrayList<>();
        String sql = "SELECT offering,receiver_code From Exchange Where receiving=? AND confirm_offer=1 AND confirm_receiver=0";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ans.add(new PurchaseMessage(user, rs.getString("offering"), rs.getInt("receiver_code"), ""));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return ans;
    }


    public int getVCodeFromExchange(String offer_user, int myVacationCode, String user) {
        int ans=-1;
        String sql = "SELECT offer_code From Exchange Where receiver_code=? AND offering=? AND receiving=?";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, myVacationCode);
            pstmt.setString(2, offer_user);
            pstmt.setString(3, user);


            ResultSet rs = pstmt.executeQuery();
            rs.next();
            ans = rs.getInt("offer_code");

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return ans;
    }
}

