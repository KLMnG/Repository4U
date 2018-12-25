package Models;

import General.VacationData;

import java.time.LocalDate;

public class VacationInfoLoggedinModel {

    private VacationModel vacationModel;
    private UserModel userModel;
    private  PurchaseVacationModel purchaseVacationModel;
    private LocalDate localDate;
    public VacationInfoLoggedinModel(UserModel userModel,VacationModel vacationModel,PurchaseVacationModel purchaseVacationModel) {
        this.vacationModel = vacationModel;
        this.userModel = userModel;
        this.purchaseVacationModel = purchaseVacationModel;
        this.localDate = LocalDate.now();
    }

    public boolean AskToPurchase() {
        return this.purchaseVacationModel.addPurchaseVacation(vacationModel.getSelectedVacationData().getCode(),vacationModel.getSelectedVacationData().getSeller().getUsername(),localDate.toString());
    }

    public VacationData getSelectedVacationData() {
        return vacationModel.getSelectedVacationData();
    }
}
