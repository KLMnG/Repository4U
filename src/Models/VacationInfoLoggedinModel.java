package Models;

import General.VacationData;

public class VacationInfoLoggedinModel {

    private VacationModel vacationModel;
    private UserModel userModel;
    private  PurchaseVacationModel purchaseVacationModel;
    public VacationInfoLoggedinModel(UserModel userModel,VacationModel vacationModel,PurchaseVacationModel purchaseVacationModel) {
        this.vacationModel = vacationModel;
        this.userModel = userModel;
        this.purchaseVacationModel = purchaseVacationModel;
    }

    public void AskToPurchase() {
        this.purchaseVacationModel.addPurchaseVacation(vacationModel.getSelectedVacationData().getCode(),vacationModel.getSelectedVacationData().getSeller().getUsername());
    }

    public VacationData getSelectedVacationData() {
        return vacationModel.getSelectedVacationData();
    }
}
