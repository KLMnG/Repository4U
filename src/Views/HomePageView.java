package Views;

import Controllers.AController;
import Controllers.HomePageController;
import javafx.event.ActionEvent;

public class HomePageView implements IView{

    private HomePageController controller;

    public void Signin(ActionEvent actionEvent) {
        this.controller.openLoginWindow();
    }

    public void Signup(ActionEvent actionEvent) {
        this.controller.openRegisterWindow();
    }
    @Override
    public void setController(AController controller) {
        this.controller = (HomePageController) controller;
    }

}
