package Models;

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


    public void getVacations() {
        vacationModel.getVacationData();
    }
}
