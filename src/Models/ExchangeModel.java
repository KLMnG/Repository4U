package Models;

import General.DBConnection;
import General.PurchaseMessage;
import General.User;
import General.VacationData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ExchangeModel {

    private VacationModel vacationModel;
    private UserModel userModel;
    private PurchaseVacationModel purchaseVacationModel;

    public ExchangeModel(VacationModel vacationModel, UserModel userModel, PurchaseVacationModel purchaseVacationModel) {
        this.vacationModel = vacationModel;
        this.userModel = userModel;
        this.purchaseVacationModel = purchaseVacationModel;
    }

    public VacationData getSelectedVacationData() {
        return vacationModel.getSelectedVacationData();
    }

    public List<VacationData> getUserExchangeVacations() {
        return this.userModel.getMyVacation(UserModel.getUsername(), VacationData.State.FOR_EXCHANGE);
    }


    public boolean AskToExchange(VacationData vacation) {
        return addExchangeVacation(vacationModel.getSelectedVacationData().getCode(),vacation.getCode(), vacationModel.getSelectedVacationData().getSeller().getUsername(),vacation.getSeller().getUsername());
    }

    public boolean addExchangeVacation(int receiveCodeVacation,int offerCodeVacation, String receiver, String offer ){

         DBConnection con=new DBConnection();
            String sql = "INSERT INTO Exchange(offering,receiving,offer_code,receiver_code) Values(?,?,?,?)";

            try (Connection conn = con.getSQLLiteDBConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, offer);
                pstmt.setString(2, receiver);
                pstmt.setInt(3, offerCodeVacation);
                pstmt.setInt(4, receiveCodeVacation);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                return false;
            }

        return true;
    }
    public VacationData getOfferedVacationData(String offer_user, int myVacationCode) {
        int code = vacationModel.getVCodeFromExchange(offer_user, myVacationCode, UserModel.getUsername());
        return this.vacationModel.readByID(code);
    }

    public List<PurchaseMessage> getofferings() {
        return this.vacationModel.getRequestingOffers(UserModel.getUsername());
    }
}
