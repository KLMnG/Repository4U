package Models;

import General.DBConnection;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VacationModel {
    private DBConnection con;


    public Map<String,List<String>> read() {
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

            while (rs.next()) {
                ArrayList tmp = new ArrayList<String>();
                tmp.add(rs.getString("flight_company"));
                tmp.add(rs.getString("departure_date"));
                tmp.add(rs.getString("departure_from"));
                tmp.add(rs.getString("destination"));
                tmp.add(rs.getString("ticket_type"));
                tmp.add(rs.getString("seller"));
                tmp.add(rs.getString("weight"));
                tmp.add(rs.getString("height"));
                tmp.add(rs.getString("width"));
                tmp.add(rs.getString(CounntTravelers()+""));
                tmp.add(rs.getString("time_to_stay"));
                tmp.add(rs.getString("vacation_type"));
                tmp.add(rs.getString("address"));
                tmp.add(rs.getString("rate"));
                tmp.add(rs.getString("Price"));
                vacations.put(rs.getString("code"), tmp);
            }
        } catch (SQLException e) {
        }
        return vacations;
    }


    public int CounntTravelers(){
        return 0;
    }

}
