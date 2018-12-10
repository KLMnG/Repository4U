package Models;


import General.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModelTicketDB {
    private DBConnection con;

    public ModelTicketDB() {
        con = new DBConnection();
    }

    public void addTicket(String UserName, String ticketCode, String flightCompanyName, String depatureDate,
                          boolean flightBack, int weight, int height, int width, String destinationCountry,
                          String typeOfPassenger, String departureCountry, String timeOfStay,
                          String vacationType, String hotel) {

        String luggageCode = getLCode(weight, height, width);
        String vacationCode = getVCode(timeOfStay, vacationType, hotel);
        int flightBackValue = 0;
        if (flightBack)
            flightBackValue = 1;

        String sql = "INSERT INTO Tickets(code,flight_company,departure_date," +
                "includes_flight_back,luggage,destination," +
                "ticket_type,departuer_from,vacation,seller) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ticketCode);
            pstmt.setString(2, flightCompanyName);
            pstmt.setString(3, depatureDate);
            pstmt.setInt(4, flightBackValue);
            pstmt.setString(5, luggageCode);
            pstmt.setString(6, destinationCountry);
            pstmt.setString(7, typeOfPassenger);
            pstmt.setString(8, departureCountry);
            pstmt.setString(9, vacationCode);
            pstmt.setString(10, UserName);
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
        String sql = "INSERT INTO Luggages(weight,height,height) VALUES(?,?,?)";
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, weight);
            pstmt.setInt(2, height);
            pstmt.setInt(3, width);
            pstmt.executeUpdate();
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
        return getLCode(weight,height,width);


    }
}
