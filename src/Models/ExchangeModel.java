package Models;

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
        return this.purchaseVacationModel.addPurchaseVacation(vacationModel.getSelectedVacationData().getCode(), vacationModel.getSelectedVacationData().getSeller().getUsername(), LocalDate.now().toString());
    }


    public VacationData getOfferedVacationData(String offer_user, int myVacationCode) {
        int code = vacationModel.getVCodeFromExchange(offer_user, myVacationCode, UserModel.getUsername());
        return this.vacationModel.readByID(code);
    }

    public List<PurchaseMessage> getofferings() {
        return this.vacationModel.getRequestingOffers(UserModel.getUsername());
    }
}
