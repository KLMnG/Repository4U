package Models;

public class HomePageModel {

    private UserModel userModel;
    private VacationModel vacationModel;

    public HomePageModel(UserModel userModel, VacationModel vacationModel) {
        this.userModel = userModel;
        this.vacationModel = vacationModel;
    }


}
