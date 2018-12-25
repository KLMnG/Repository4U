package Models;


import General.DBConnection;
import General.TicketData;
import General.User;
import General.VacationData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModelTicketDB {
    private DBConnection con;

    public ModelTicketDB() {
        con = new DBConnection();
    }

    private int getVCode(VacationData vacationData) {
        String sql = "SELECT max(code) as code "
                + "FROM Vacations WHERE owner = ?,time_to_Stay=?,vacation_type=?,hotel=?,state=?,price=?";

        int code = -1;
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, UserModel.getUsername());
            pstmt.setInt(2, vacationData.getDays());
            pstmt.setString(3, vacationData.getVacationType());
            pstmt.setString(4, vacationData.getHotel().getCode());
            pstmt.setString(5, vacationData.getState());
            pstmt.setInt(6, vacationData.getPrice());
            ResultSet rs = pstmt.executeQuery();


            code = (rs.getInt("code"));
        } catch (SQLException e) {
        }
        return code;
    }

    public void saveTickets(VacationData vacationData) {
        int vacationCode = -1;
        vacationCode = createVacationAndReturn(vacationData);
        for (TicketData td :
                vacationData.getTicketData()) {
            saveTicket(td,vacationCode);

        }


    }

    private void saveTicket(TicketData ticketData, int vacationCode) {
        String sql = "INSERT INTO Tickets(code,flight_company,departure_date," +
                "includes_flight_back,destination," +
                "ticket_type,departure_from,vacation,weight,height,width) VALUES(?,?,?,?,?,?,?,?,?,?,?)";


        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, ticketData.getCode());
            pstmt.setString(2, ticketData.getAirline());
            pstmt.setString(3, ticketData.getDepart());
            pstmt.setInt(4, ticketData.getIncludes_flight_back()? 1 : 0);
            pstmt.setString(5, ticketData.getTo());
            pstmt.setString(6, ticketData.getTicketType());
            pstmt.setString(7, ticketData.getFrom());
            pstmt.setInt(8,vacationCode );
            pstmt.setInt(9, ticketData.getWeight());
            pstmt.setInt(10, ticketData.getHeight());
            pstmt.setInt(11, ticketData.getWidth());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    private int createVacationAndReturn(VacationData vacationData) {
        String sql = "INSERT INTO Vacations(time_to_stay,vacation_type,hotel,owner,state,price) VALUES(?,?,?,?,?,?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vacationData.getDays());
            pstmt.setString(2, vacationData.getVacationType());
            pstmt.setString(3, vacationData.getHotel().getCode());
            pstmt.setString(4, UserModel.getUsername());
            pstmt.setString(5, vacationData.getState());
            pstmt.setInt(6, vacationData.getPrice());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        int code = getVCode(vacationData);

        return code;
    }


    public void removeVacation(String code) {
        deleteTickets(code);
        deleteVacation(code);
    }

    private void deleteVacation(String code) {
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

    private void deleteTickets(String code) {
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
