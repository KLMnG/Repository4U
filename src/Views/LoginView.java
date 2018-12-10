package Views;

import Controllers.LoginController;
import Controllers.AController;

public class LoginView implements IView{

    private LoginController controller;


    @Override
    public void setController(AController controller) {
        this.controller = (LoginController)controller;
    }
}
