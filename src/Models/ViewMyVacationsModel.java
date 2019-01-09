package Models;

import General.VacationData;

import java.util.ArrayList;
import java.util.List;

public class ViewMyVacationsModel {
    VacationModel vacationModel;
    UserModel userModel;

    public ViewMyVacationsModel(UserModel userModel, VacationModel vacationModel){
        this.userModel = userModel;
        this.vacationModel = vacationModel;
    }



    public List<VacationData> getVacations() {
        List<VacationData> forSale = userModel.getMyVacation(UserModel.getUsername(),VacationData.State.FOR_SELL);
        List <VacationData> forExchange = userModel.getMyVacation(UserModel.getUsername(),VacationData.State.FOR_EXCHANGE);
        List <VacationData> invisibles = userModel.getMyVacation(UserModel.getUsername(),VacationData.State.INVISIBLE);
        List<VacationData> allVaca = new ArrayList<>();
        allVaca.addAll(forExchange);
        allVaca.addAll(forSale);
        allVaca.addAll(invisibles);
        return allVaca;
    }

    public void ChangeState(VacationData vacationData, VacationData.State state) {
        vacationModel.setNewStateForVacation(vacationData.getCode(), state);

    }

    public void setSelectedVacationCode(int selectedVacationCode) {

        this.vacationModel.setSelectedVacationForChangeCode(selectedVacationCode);    }



    public VacationData getSelectedVacationCode() {

        return this.vacationModel.getSelectedVacationForChangeCode();    }
}
