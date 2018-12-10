package Models;

import General.DBConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class PurchaseVacationModel {

    private DBConnection con;

    public PurchaseVacationModel() {
        con = new DBConnection();
    }

    public void addPurchaseVacation(String code_ticket, String seller){

        String userNameSeller = seller;
        String userNameBuyer = UserModel.getUsername();

        String sql = "INSERT INTO RequestPurchase(seller,buyer,code_vacation,confirm_seller,confirm_buyer) Values(?,?,?,?,?)";

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userNameSeller);
            pstmt.setString(2, userNameBuyer);
            pstmt.setString(3, code_ticket);
            pstmt.setInt(4, 0);
            pstmt.setInt(5, 1);
            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public void confirmVacation(String seller, String buyer, String code_vacation){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm vacation");
        alert.setContentText("Are you sure you want to sell?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            confirmVacationInDB(seller,buyer,code_vacation);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private void confirmVacationInDB(String seller, String buyer, String code_vacation){
        String sql = "UPDATE RequestPurchase SET confirm_seller = ? "+
                "WHERE seller = ? , buyer = ? , code_vacation = ?";;

        try (Connection conn = con.getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, 1);
            pstmt.setString(2, seller);
            pstmt.setString(3, buyer);
            pstmt.setString(4, code_vacation);
            // update
            pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
}
