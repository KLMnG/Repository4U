package Models;

import General.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseVacationModel {

    private DBConnection con;

    public PurchaseVacationModel() {
        con = new DBConnection();
    }

    public boolean addPurchaseVacation(int code_ticket, String userNameSeller){

        String userNameBuyer = UserModel.getUsername();
        if(!isExist(userNameSeller,code_ticket)) {
            String sql = "INSERT INTO RequestPurchase(seller,buyer,code_vacation,confirm_seller,confirm_buyer) Values(?,?,?,?,?)";

            try (Connection conn = con.getSQLLiteDBConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, userNameSeller);
                pstmt.setString(2, userNameBuyer);
                pstmt.setInt(3, code_ticket);
                pstmt.setInt(4, 0);
                pstmt.setInt(5, 1);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

    public boolean isExist(String seller, int codeTicket){
        String sql = "SELECT buyer FROM RequestPurchase WHERE seller = ? AND code_vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, seller);
            pstmt.setInt(2, codeTicket);

            ResultSet rs = pstmt.executeQuery();

            String back = rs.getString("buyer");
            if(!back.equals("")) {
                return true;
            }

        } catch (SQLException e) {
        }
        return false;
    }

    public Map<String,String> listOfBuyers(String seller){
        Map<String,String> buyers = new HashMap<>();
        String sql = "SELECT buyer,code_vacation "
                + "FROM RequestPurchase WHERE seller = ? ";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,seller);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                String buyer = (rs.getString("buyer"));
                String code =  (rs.getString("code_vacation"));
                buyers.put(code,buyer);
            }
        } catch (SQLException e) {
        }
        // now we have map with all the codeVacation and his buyers
        return buyers;
    }

    public void confirmVacationInDB(String seller, String buyer, String code_ticket){
        String sql = "UPDATE RequestPurchase SET confirm_seller = ? "+
                    "WHERE seller = ? AND buyer = ? AND code_vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 1);
            pstmt.setString(2, seller);
            pstmt.setString(3, buyer);
            pstmt.setString(4, code_ticket);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public boolean confirmation(String seller, String buyer){

        List <Integer> arrl = null;

        String sql = "SELECT confirm_seller,confirm_buyer"
                + "FROM RequestPurchase WHERE seller = ?, buyer = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,seller);
            pstmt.setString(2,buyer);

            ResultSet rs  = pstmt.executeQuery();

            arrl = new ArrayList();

            arrl.add(rs.getInt("confirm_seller"));
            arrl.add(rs.getInt("confirm_buyer"));

        } catch (SQLException e) {
        }
        if(arrl == null)
            return false;
        if(arrl.get(0) == 1 && arrl.get(1) == 1)
            return true;
        return false;
    }
/*
    public List<String> read(String code_ticket, String code_luggages, String code_vacation, String code_hotel) {
        List<String> vacationInfo = null;
        String sql = "SELECT Tickets.code, flight_company,departure_date," +
                "departure_from, destination, ticket_type, seller, " +
                "Luggages.weight,Luggages.height,Luggages.width" +
                ",Vacations.time_to_stay, Vacations.vacation_type, " +
                ",Hotels.address, Hotels.rate, Price "
                + "FROM Tickets, Luggages, Vacation, Hotels "+
                "WHERE Tickets.code = ?, Luggages.code = ?, Vacation.code = ?, Hotel.code = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,code_ticket);
            pstmt.setString(2,code_luggages);
            pstmt.setString(2,code_vacation);
            pstmt.setString(2,code_hotel);

            ResultSet rs  = pstmt.executeQuery();

            vacationInfo = new ArrayList<>();

            while (rs.next()) {
                vacationInfo.add(rs.getString("Tickets.code"));
                vacationInfo.add(rs.getString("flight_company"));
                vacationInfo.add(rs.getString("departure_date"));
                vacationInfo.add(rs.getString("departure_from"));
                vacationInfo.add(rs.getString("destination"));
                vacationInfo.add(rs.getString("ticket_type"));
                vacationInfo.add(rs.getString("seller"));
                vacationInfo.add(rs.getString("weight"));
                vacationInfo.add(rs.getString("height"));
                vacationInfo.add(rs.getString("width"));
                //tmp.add(rs.getString(CounntTravelers()+""));
                vacationInfo.add(rs.getString("time_to_stay"));
                vacationInfo.add(rs.getString("vacation_type"));
                vacationInfo.add(rs.getString("address"));
                vacationInfo.add(rs.getString("rate"));
                vacationInfo.add(rs.getString("Price"));
            }
        } catch (SQLException e) {
        }
        return vacationInfo;
    }
*/
    public void addPayment(String creditNumber, String expiry) {
        String userNameBuyer = UserModel.getUsername();

        String sql = "INSERT INTO Payment(buyer,creditcard,expiry) VALUES(?,?,?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userNameBuyer);
            pstmt.setString(2, creditNumber);
            pstmt.setString(3, expiry);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }





//    public static void main(String[] args) {
//        PurchaseVacationModel p = new PurchaseVacationModel();
//        p.addPayment("1234", "02-20");
//    }
}
