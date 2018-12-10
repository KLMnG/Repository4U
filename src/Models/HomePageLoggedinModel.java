package Models;

import General.User;

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

    public void setSignOut(String UserName, String Password){
        userModel.Signout(UserName,Password);
    }

    public String GetUserName(){
        return UserModel.getUsername();

    }

    public String GetPassword(){
        return UserModel.getPassword();

    }


}