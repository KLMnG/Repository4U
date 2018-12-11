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
                          String typeOfPassenger, String departureCountry, String vacationCode) {

        int flightBackValue = 0;
        if (flightBack)
            flightBackValue = 1;

        String sql = "INSERT INTO Tickets(code,flight_company,departure_date," +
                "includes_flight_back,luggage,destination," +
                "ticket_type,departuer_from,vacation,seller,price) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(9, vacationCode);
            pstmt.setString(10, UserName);
            pstmt.setInt(11, Integer.parseInt(price));
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }


    }

    private String getVCode(String timeOfStay, String vacationType, String hotel) {
        String sql = "SELECT code"
                + "FROM Vacations WHERE time_Of_Stay = ?,vacation_Type = ?,hotel=?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, timeOfStay);
            pstmt.setString(2, vacationType);
            pstmt.setString(3, hotel);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {

                sql = (rs.getString("code"));
            }
        } catch (SQLException e) {
        }
        if (sql.equals(""))
            sql = createVacationAndReturn(timeOfStay, vacationType, hotel);
        return sql;
    }

    private String createVacationAndReturn(String timeOfStay, String vacationType, String hotel) {
        String sql = "INSERT INTO Vacations(time_Of_Stay,vacation_Type,hotel) VALUES(?,?,?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, timeOfStay);
            pstmt.setString(2, vacationType);
            pstmt.setString(3, hotel);


        } catch (SQLException e) {
        }
        if (sql.equals(""))
            sql = getVCode(timeOfStay, vacationType, hotel);

        return sql;
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
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
        return getLCode(weight, height, width);


    }

    public void saveTickets(List<String> vacation, List<List<String>> tickets) {
        String vacationCode = null;
        if (vacation.size() > 0)
            vacationCode = createVacationAndReturn(vacation.get(0), vacation.get(1), vacation.get(2));
        saveTickets(vacationCode, tickets);

    }

    private void saveTickets(String vacationCode, List<List<String>> tickets) {
        String luggageCode;
        for (List<String> ticket : tickets) {
            if (ticket.size() == 11)
                luggageCode = getLCode(Integer.getInteger(ticket.get(8)).intValue(), Integer.getInteger(ticket.get(9)).intValue(), Integer.getInteger(ticket.get(10)).intValue());
            else
                luggageCode = null;
            addTicketWithLuggage(vacationCode, luggageCode, ticket);

        }
    }

    private void addTicketWithLuggage(String vacationCode, String luggageCode, List<String> ticket) {
        addTicket(UserModel.getUsername(), vacationCode,ticket.get(0), ticket.get(1), ticket.get(6), ticket.get(5).equals("1"), luggageCode, ticket.get(7), ticket.get(4), ticket.get(3), vacationCode);

    }



}
