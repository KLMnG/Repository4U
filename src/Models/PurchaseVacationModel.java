package Models;

import General.DBConnection;
import General.PurchaseMessage;
import General.VacationData;

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
    private int VacationCode;
    private String Seller ;

    public int getVacationCode() {
        return VacationCode;
    }

    public void setVacationCode(int vacationCode) {
        VacationCode = vacationCode;
    }

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public String getBuyer() {
        return Buyer;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    private String Buyer;


    public PurchaseVacationModel() {
        con = new DBConnection();
    }

    public boolean addPurchaseVacation(int codeVacation, String userNameSeller){

        String userNameBuyer = UserModel.getUsername();
        if(!isExist(userNameSeller,codeVacation)) {
            String sql = "INSERT INTO RequestPurchase(seller,buyer,code_vacation,confirm_seller,confirm_buyer) Values(?,?,?,?,?)";

            try (Connection conn = con.getSQLLiteDBConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, userNameSeller);
                pstmt.setString(2, userNameBuyer);
                pstmt.setInt(3, codeVacation);
                pstmt.setInt(4, 0);
                pstmt.setInt(5, 1);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

    public boolean isExist(String seller, int codeVacation){
        String sql = "SELECT buyer FROM RequestPurchase WHERE seller = ? AND code_vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, seller);
            pstmt.setInt(2, codeVacation);

            ResultSet rs = pstmt.executeQuery();

            String back = rs.getString("buyer");
            if(!back.equals("")) {
                return true;
            }

        } catch (SQLException e) {
        }
        return false;
    }

    public List<PurchaseMessage> listOfBuyers(String seller){
        String sql = "SELECT buyer,code_vacation "
                + "FROM RequestPurchase WHERE seller = ? ";

        List<PurchaseMessage> lstmessage = null;
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,seller);
            ResultSet rs  = pstmt.executeQuery();

            lstmessage = new ArrayList<PurchaseMessage>();
            while (rs.next()) {
                String buyer = (rs.getString("buyer"));
                int code = (rs.getInt("code_vacation"));
                PurchaseMessage msg = new PurchaseMessage(seller,buyer,code,"");
                lstmessage.add(msg);
            }
        } catch (SQLException e) {
        }
        // now we have map with all the codeVacation and his buyers
        return lstmessage;
    }
    public List<PurchaseMessage> listOfSellers(String buyer){
        String sql = "SELECT seller,code_vacation "
                + "FROM RequestPurchase WHERE buyer = ? ";

        List<PurchaseMessage> lstmessage = null;
        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,buyer);
            ResultSet rs  = pstmt.executeQuery();

            lstmessage = new ArrayList<PurchaseMessage>();
            while (rs.next()) {
                String seller = (rs.getString("seller"));
                int code =  (rs.getInt("code_vacation"));
                PurchaseMessage msg = new PurchaseMessage(seller,buyer,code,"");
                lstmessage.add(msg);
            }
        } catch (SQLException e) {
        }
        // now we have map with all the codeVacation and his buyers
        return lstmessage;
    }

    public void confirmVacationInDB(String seller, String buyer, int code_vacation){
        String sql = "UPDATE RequestPurchase SET confirm_seller = ? "+
                    "WHERE seller = ? AND buyer = ? AND code_vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 1);
            pstmt.setString(2, seller);
            pstmt.setString(3, buyer);
            pstmt.setInt(4, code_vacation);

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

    public void removeRequest(String  seller, String buyer, int code_vacation){
        String sql = "DELETE FROM RequestPurchase WHERE seller = ? AND buyer = ? AND code_vacation = ?";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, seller);
            pstmt.setString(2, buyer);
            pstmt.setInt(3, code_vacation);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
}
