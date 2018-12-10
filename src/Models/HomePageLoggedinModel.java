package Models;

public class HomePageLoggedinModel {

    private UserModel userModel;
    private VacationModel vacationModel;

    public HomePageLoggedinModel(UserModel userModel, VacationModel vacationModel) {
        this.userModel = userModel;
        this.vacationModel = vacationModel;
    }

    public String getUserName() {
        return UserModel.getFirstName();
    }
}
