package Models;


import General.DBConnection;
import General.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelTicketDB {
    private DBConnection con;

    public ModelTicketDB() {
        con = new DBConnection();
    }

    public void addTicket(String UserName, String ticketCode, String price, String flightCompanyName, String depatureDate,
                          boolean flightBack, String luggageCode, String destinationCountry,
                          String typeOfPassenger, String departureCountry, int vacationCode) {

        int flightBackValue = 0;
        if (flightBack)
            flightBackValue = 1;
        if (luggageCode != null) {
            String sql = "INSERT INTO Tickets(code,flight_company,departure_date," +
                    "includes_flight_back,luggage,destination," +
                    "ticket_type,departure_from,vacation,seller,price) VALUES(?,?,?,?,?,?,?,?,?,?,?)";


            try (Connection conn = con.getSQLLiteDBConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {


                pstmt.setString(1, ticketCode);
                pstmt.setString(2, flightCompanyName);
                pstmt.setString(3, depatureDate);
                pstmt.setInt(4, flightBackValue);
                pstmt.setInt(5, Integer.parseInt(luggageCode));
                pstmt.setString(6, destinationCountry);
                pstmt.setString(7, typeOfPassenger);
                pstmt.setString(8, departureCountry);
                pstmt.setInt(9, vacationCode);
                pstmt.setString(10, UserName);
                pstmt.setInt(11, Integer.parseInt(price));
                pstmt.executeUpdate();
            } catch (SQLException e) {
            }
        }
        else{
            String sql = "INSERT INTO Tickets(code,flight_company,departure_date," +
                    "includes_flight_back,destination," +
                    "ticket_type,departure_from,vacation,seller,price) VALUES(?,?,?,?,?,?,?,?,?,?)";


            try (Connection conn = con.getSQLLiteDBConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {


                pstmt.setString(1, ticketCode);
                pstmt.setString(2, flightCompanyName);
                pstmt.setString(3, depatureDate);
                pstmt.setInt(4, flightBackValue);
                pstmt.setString(5, destinationCountry);
                pstmt.setString(6, typeOfPassenger);
                pstmt.setString(7, departureCountry);
                pstmt.setInt(8, vacationCode);
                pstmt.setString(9, UserName);
                pstmt.setInt(10, Integer.parseInt(price));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
                System.out.println(e.getMessage());

            }
        }


    }

    private int getVCode(String username) {
        String sql = "SELECT max(code) as code "
                + "FROM Vacations WHERE seller = ?";

        int code = -1;
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                code = (rs.getInt("code"));
            }
        } catch (SQLException e) {
        }
//        if (sql.equals(""))
//            sql = createVacationAndReturn("",timeOfStay, vacationType, hotel);
        return code;
    }

    private int createVacationAndReturn(String username,String timeOfStay, String vacationType, String hotel) {
        String sql = "INSERT INTO Vacations(seller,time_Of_Stay,vacation_Type,hotel) VALUES(?,?,?,?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setInt(2, Integer.parseInt(timeOfStay));
            pstmt.setString(3, vacationType);
            pstmt.setString(4, hotel);


        } catch (SQLException e) {
        }
        int code = getVCode(username);

        return code;
    }

    private String getLCode(int weight, int height, int width) {
        String sql = "SELECT code"
                + "FROM Luggage WHERE weight = ?,weight = ?,weight=?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, weight);
            pstmt.setInt(2, height);
            pstmt.setInt(3, width);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                sql = (rs.getString("code"));
            }
        } catch (SQLException e) {
        }
        if (sql.equals(""))
            sql = createLuggageAndReturn(weight, height, width);
        return sql;
    }

    private String createLuggageAndReturn(int weight, int height, int width) {
        String sql = "INSERT INTO Luggages(weight,height,height) VALUES(?,?,?)";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, weight);
            pstmt.setInt(2, height);
            pstmt.setInt(3, width);
            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
        return getLCode(weight, height, width);


    }

    public void saveTickets(List<String> vacation, List<List<String>> tickets) {
        int vacationCode = -1;
        if (vacation.size() > 0 && !(vacation.get(0).trim().isEmpty() || vacation.get(1).trim().isEmpty() || vacation.get(2).trim().isEmpty()))
            vacationCode = createVacationAndReturn(UserModel.getUsername(),vacation.get(0), vacation.get(1), vacation.get(2));
        else
            vacationCode = createVacationAndReturn(UserModel.getUsername());
        saveTickets(vacationCode, tickets);

    }

    private int createVacationAndReturn(String username) {
        String sql = "INSERT INTO Vacations(seller) VALUES(?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
         int code = getVCode(username);

        return code;
    }

    private void saveTickets(int vacationCode, List<List<String>> tickets) {
        String luggageCode;
        for (List<String> ticket : tickets) {
            if (ticket.size() == 11)
                luggageCode = getLCode(Integer.getInteger(ticket.get(8)).intValue(), Integer.getInteger(ticket.get(9)).intValue(), Integer.getInteger(ticket.get(10)).intValue());
            else
                luggageCode = null;
            addTicketWithLuggage(vacationCode, luggageCode, ticket);

        }
    }

    private void addTicketWithLuggage(int vacationCode, String luggageCode, List<String> ticket) {
        addTicket(UserModel.getUsername(), ticket.get(1),ticket.get(0), ticket.get(2), ticket.get(6), ticket.get(5).equals("1"), luggageCode, ticket.get(7), ticket.get(4), ticket.get(3), vacationCode);
    }


    public void removeVacation(String code){
        deleteTickets(code);
        deleteVacation(code);
    }

    private void deleteVacation(String code){
        String sql = "DELETE FROM Vacations WHERE code = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, Integer.parseInt(code));
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void deleteTickets(String code){
        String sql = "DELETE FROM Tickets WHERE vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, Integer.parseInt(code));
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
