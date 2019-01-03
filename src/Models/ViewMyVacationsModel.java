package Models;

import General.VacationData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewMyVacationsModel {
    VacationModel vacationModel;
    UserModel userModel;
    List<VacationData> forSale;
    List <VacationData> forExchange;

    public ViewMyVacationsModel(UserModel userModel, VacationModel vacationModel){
        this.userModel = userModel;
        this.vacationModel = vacationModel;
    }



    public List<VacationData> getVacations() {
        List<VacationData> forSale = userModel.getMyVacation(UserModel.getUsername(),VacationData.State.FOR_SELL);
        List <VacationData> forExchange = userModel.getMyVacation(UserModel.getUsername(),VacationData.State.FOR_EXCHANGE);
        List<VacationData> allVaca = new ArrayList<>();
        allVaca.addAll(forExchange);
        allVaca.addAll(forSale);
        return allVaca;
    }

    public void ChangeState(VacationData vacationData, String state) {
        if(state.equals("for sale"))
        vacationModel.setNewStateForVacation(vacationData.getCode()+"", VacationData.State.FOR_SELL);
        else
            vacationModel.setNewStateForVacation(vacationData.getCode()+"", VacationData.State.FOR_EXCHANGE);
    }

    public void setSelectedVacationCode(int selectedVacationCode) {

        this.vacationModel.setSelectedVacationForChangeCode(selectedVacationCode);    }



    public VacationData getSelectedVacationCode() {

        return this.vacationModel.getSelectedVacationForChangeCode();    }
}
