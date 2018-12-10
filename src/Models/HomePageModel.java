package Models;

public class HomePageModel {

    private UserModel userDBModel;
    private VacationModel vacationModel;

    public HomePageModel(UserModel userDBModel, VacationModel vacationModel) {
        this.userDBModel = userDBModel;
        this.vacationModel = vacationModel;
    }


}
