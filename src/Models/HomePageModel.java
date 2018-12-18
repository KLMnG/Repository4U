package Models;

import General.VacationData;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

public class HomePageModel {

    private UserModel userModel;
    private VacationModel vacationModel;

    public HomePageModel(UserModel userModel, VacationModel vacationModel) {
        this.userModel = userModel;
        this.vacationModel = vacationModel;
    }


    public Map<Integer, VacationData> getVacations() {
        this.vacationModel.read();
        return vacationModel.getVacationData();
    }

    public void setSelectedVacationCode(int selectedVacation) {
        this.vacationModel.setSelectedVacationCode(selectedVacation);
    }
}
